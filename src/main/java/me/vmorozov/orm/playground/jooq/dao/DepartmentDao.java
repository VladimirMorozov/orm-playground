package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.domain.DepartmentInfo;
import me.vmorozov.orm.playground.jooq.util.EpicMapperBuilder;
import org.jooq.DSLContext;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

import static me.vmorozov.orm.playground.jooq.generated.Tables.COMPANY;
import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.fields;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.mapToOptional;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.pluralAliases;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixed;

@Repository
public class DepartmentDao {



    private static final JdbcMapper<DepartmentInfo> originalDepartmentMapper = JdbcMapperFactory
        .newInstance()
        .unorderedJoin()
        .addKeys("department_id", "employee_id", "company_id")
        .addAliases(pluralAliases(EMPLOYEE))
        .newMapper(DepartmentInfo.class);

    private static final JdbcMapper<DepartmentInfo> departmentMapper = EpicMapperBuilder
        .forClass(DepartmentInfo.class)
        .build();

    private DSLContext dslContext;

    public DepartmentDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Optional<DepartmentInfo> getDepartmentInfo(int departmentId) {
        ResultSet resultSet = dslContext.select(fields(prefixed(EMPLOYEE, COMPANY), DEPARTMENT.asterisk()))
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.equal(COMPANY.ID))
            .join(EMPLOYEE).on(DEPARTMENT.ID.eq(EMPLOYEE.DEPARTMENT_ID))
            .where(DEPARTMENT.ID.equal(departmentId))
            .fetchResultSet();

        return mapToOptional(resultSet, departmentMapper);
    }
}
