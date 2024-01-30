package me.vmorozov.orm.playground.jdbc_template;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.FlatDepartmentSearch;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class DepartmentSearchJdbcDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    // There is no easy way to map a list of objects without SFM
    RowMapper<DepartmentTableRow> mapper =
            JdbcTemplateMapperFactory.newInstance().newRowMapper(DepartmentTableRow.class);

    private static final PropertyNamingStrategy.SnakeCaseStrategy SNAKE_CASE_STRATEGY = new PropertyNamingStrategy.SnakeCaseStrategy();

    public List<DepartmentTableRow> fetchDepartmentTableData(DepartmentSearch departmentSearch, Pageable pageable) {
        FlatDepartmentSearch search = FlatDepartmentSearch.fromDepartmentSearch(departmentSearch);

        String baseSql = generateSearchSQL(search);

        String sql = """
                SELECT
                    d.id            as id,
                    d.name          as department_name,
                    head.name       as department_head_name,
                    c.name          as company_name,
                    count(e.id)     as employee_count
                FROM""" + " " + baseSql + "\n" +
                (pageable.getSort().isSorted() ?
                        ("ORDER BY " + sorting(pageable.getSort(), Set.of("employeeCount", "departmentHeadName"))) : ""
                ) + "\n" +
                "LIMIT :pageSize" + "\n" +
                "OFFSET :offset";

        CombinedSqlParameterSource paramSource = new CombinedSqlParameterSource(search, pageable);
        return jdbcTemplate.query(sql, paramSource, mapper);
    }

    public Long fetchDepartmentTableCount(DepartmentSearch departmentSearch) {
        FlatDepartmentSearch search = FlatDepartmentSearch.fromDepartmentSearch(departmentSearch);

        String countSql = "SELECT COUNT(*) FROM (SELECT 1 FROM " + generateSearchSQL(search) + ") as sub";

        return jdbcTemplate.queryForObject(countSql, new CombinedSqlParameterSource(search), Long.class);
    }

    public String generateSearchSQL(FlatDepartmentSearch search){
        return """
            department d
            JOIN company c on d.company_id = c.id
            LEFT JOIN employee e on d.id = e.department_id
            LEFT JOIN employee head on d.head_id = head.id
            """ +
                where(
                        search.getDepartmentName() != null ? "lower(d.name) LIKE '%' || lower(:departmentName) || '%'" : null,
                        search.getDepartmentHeadName() != null ? "lower(head.name) LIKE '%' || lower(:departmentHeadName) || '%'" : null,
                        search.getCompanyName() != null ? "lower(c.name) LIKE '%' || lower(:companyName) || '%'" : null,
                        search.isMustHaveProgrammers() ? """
                            AND EXISTS (
                                SELECT id
                                FROM employee
                                WHERE employee.id = d.id
                                AND employee.position = 'programmer')
                    """ : null
                ) + "\n" +
                "GROUP BY c.id, d.id, head.name" +
                having(
                        search.getMinEmployeeCount() != null ? "count(e.id) >= :minEmployeeCount" : null,
                        search.getMaxEmployeeCount() != null ? "count(e.id) <= :maxEmployeeCount" : null
                );
    }

    private String sorting(Sort sort, Set<String> allowedPropertyNames) {
        if (!sort.stream().allMatch(o -> allowedPropertyNames.contains(o.getProperty()))) {
            throw new IllegalArgumentException("Unknown sorting column: " + sort);
        }
        return sort.stream()
                .map(o -> SNAKE_CASE_STRATEGY.translate(o.getProperty()) + " " + o.getDirection())
                .collect(Collectors.joining(", "));
    }

    public String where(String... filters) {
        String sql = filters(filters);
        if (sql.isEmpty()) {
            return "";
        }
        return "\nWHERE " + sql;
    }

    public String having(String... filters) {
        String sql = filters(filters);
        if (sql.isEmpty()) {
            return "";
        }
        return "\nHAVING " + sql;
    }

    private static String filters(String[] filters) {
        return Stream.of(filters)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n AND "));
    }

}

