package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.Range;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import me.vmorozov.orm.playground.jooq.util.OrderByBuilder;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectOrderByStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.vmorozov.orm.playground.jooq.generated.Tables.COMPANY;
import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static me.vmorozov.orm.playground.jooq.util.ConditionBuilder.condition;
import static me.vmorozov.orm.playground.jooq.util.ConditionBuilder.conditionBetween;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.fields;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixed;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixedWithoutIds;
import static org.jooq.SortOrder.ASC;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.exists;
import static org.jooq.impl.DSL.select;

@Repository
public class DepartmentSearchDao {

    private DSLContext dslContext;
    private static final EmployeeTable head = EMPLOYEE.as("head");
    private static final EmployeeTable emp = EMPLOYEE.as("emp");
    private static final Field<Integer> DEPARTMENT_ID_as_id = DEPARTMENT.ID.as("id");
    private static final Field<Integer> employee_count = DSL.field("employee_count", Integer.class);

    public DepartmentSearchDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<DepartmentTableRow> fetchDepartmentTableData_Untemplated(DepartmentSearch search, Pageable pageable) {
        Range<Integer> employeeCountRange = search.getEmployeeCount();

        return dslContext.select(fields(
            prefixed(DEPARTMENT, COMPANY),
            DEPARTMENT_ID_as_id,
            head.NAME.as("department_head_name"),
            count(emp.ID).as(employee_count))
        )
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.eq(COMPANY.ID))
            .leftJoin(EMPLOYEE.as(emp)).on(DEPARTMENT.ID.eq(emp.DEPARTMENT_ID))
            .leftJoin(EMPLOYEE.as(head)).on(head.ID.eq(DEPARTMENT.HEAD_ID))
            .where(head.NAME.containsIgnoreCase(search.getDepartmentHeadName()))
            .and(DEPARTMENT.NAME.containsIgnoreCase(search.getDepartmentName()))
            .and(COMPANY.NAME.containsIgnoreCase(search.getCompanyName()))
            .and(exists(
                select(EMPLOYEE.ID)
                    .from(EMPLOYEE)
                    .where(EMPLOYEE.DEPARTMENT_ID.eq(DEPARTMENT.ID))
                    .and(EMPLOYEE.POSITION.eq("programmer"))))
            .groupBy(DEPARTMENT.ID, COMPANY.ID, head.ID)
            .having(count(emp.ID).between(employeeCountRange.getMin(), employeeCountRange.getMax()))
            .orderBy(employee_count, DEPARTMENT.ID)
            .limit(pageable.getPageSize()).offset((int) pageable.getOffset())
            .fetchInto(DepartmentTableRow.class);
    }

    public List<DepartmentTableRow> fetchDepartmentTableData_Unextracted(DepartmentSearch search, Pageable pageable) {
        OrderByBuilder orderByBuilder = new OrderByBuilder()
            .allowSortByFieldsOf(DepartmentTableRow.class)
            .alwaysSortBy(DEPARTMENT_ID_as_id, ASC);

        return dslContext
            .select(fields(
                prefixed(DEPARTMENT, COMPANY),
                DEPARTMENT_ID_as_id,
                head.NAME.as("department_head_name"),
                count(emp.ID).as(employee_count))
            )
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.eq(COMPANY.ID))
            .leftJoin(EMPLOYEE.as(emp)).on(DEPARTMENT.ID.eq(emp.DEPARTMENT_ID))
            .leftJoin(EMPLOYEE.as(head)).on(head.ID.eq(DEPARTMENT.HEAD_ID))
            .where(condition(head.NAME::containsIgnoreCase, search.getDepartmentHeadName())
                .and(DEPARTMENT.NAME::containsIgnoreCase, search.getDepartmentName())
                .and(COMPANY.NAME::containsIgnoreCase, search.getCompanyName())
                .and(exists(
                    select(EMPLOYEE.ID)
                        .from(EMPLOYEE)
                        .where(EMPLOYEE.DEPARTMENT_ID.eq(DEPARTMENT.ID))
                        .and(EMPLOYEE.POSITION.eq("programmer"))),
                    search.isMustHaveProgrammers())
                .build())
            .groupBy(DEPARTMENT.ID, COMPANY.ID, head.ID)
            .having(conditionBetween(count(emp.ID), search.getEmployeeCount()).build())
            .orderBy(orderByBuilder.build(pageable.getSort()))
            .limit(pageable.getPageSize()).offset((int) pageable.getOffset())
            .fetchInto(DepartmentTableRow.class);
    }

    private static final OrderByBuilder orderByBuilder = new OrderByBuilder()
        .allowSortByFieldsOf(DepartmentTableRow.class)
        .alwaysSortBy(DEPARTMENT_ID_as_id, ASC);

    public List<DepartmentTableRow> fetchDepartmentTableData(DepartmentSearch search, Pageable pageable) {
        SelectSelectStep<Record> selectHead = dslContext.select(fields(
            prefixedWithoutIds(DEPARTMENT, COMPANY),
            DEPARTMENT_ID_as_id,
            head.NAME.as("department_head_name"),
            count(emp.ID).as(employee_count))
        );

        SelectOrderByStep<Record> select = createTableSelect(selectHead, search);

        return select
            .orderBy(orderByBuilder.build(pageable.getSort()))
            .limit(pageable.getPageSize())
            .offset((int) pageable.getOffset())
            .fetchInto(DepartmentTableRow.class);
    }

    public int fetchDepartmentTableCount(DepartmentSearch search) {
        return dslContext.fetchCount(createTableSelect(
            select(DEPARTMENT.ID),
            search)
        );
    }

    private <T extends Record> SelectOrderByStep<T> createTableSelect(
        SelectSelectStep<T> select,
        DepartmentSearch search
    ) {
        return select
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.eq(COMPANY.ID))
            .leftJoin(EMPLOYEE.as(emp)).on(DEPARTMENT.ID.eq(emp.DEPARTMENT_ID))
            .leftJoin(EMPLOYEE.as(head)).on(head.ID.eq(DEPARTMENT.HEAD_ID))
            .where(condition(head.NAME::containsIgnoreCase, search.getDepartmentHeadName())
                .and(DEPARTMENT.NAME::containsIgnoreCase, search.getDepartmentName())
                .and(COMPANY.NAME::containsIgnoreCase, search.getCompanyName())
                .and(exists(
                    select(EMPLOYEE.ID)
                        .from(EMPLOYEE)
                        .where(EMPLOYEE.DEPARTMENT_ID.eq(DEPARTMENT.ID))
                        .and(EMPLOYEE.POSITION.eq("programmer"))),
                    search.isMustHaveProgrammers())
                .build())
            .groupBy(DEPARTMENT.ID, COMPANY.ID, head.ID)
            .having(conditionBetween(count(emp.ID), search.getEmployeeCount()).build());
    }



}
