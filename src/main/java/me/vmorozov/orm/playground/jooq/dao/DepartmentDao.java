package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.jooq.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.jooq.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.jooq.domain.search.Range;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.vmorozov.orm.playground.jooq.generated.Tables.COMPANY;
import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static me.vmorozov.orm.playground.jooq.util.ConditionBuilder.condition;
import static me.vmorozov.orm.playground.jooq.util.ConditionBuilder.conditionBetween;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.fields;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixed;
import static org.jooq.impl.DSL.count;

@Repository
public class DepartmentDao {

    private DSLContext dslContext;
    private static final EmployeeTable head = EMPLOYEE.as("head");
    private static final EmployeeTable emp = EMPLOYEE.as("emp");
    private static final Field<Integer> employeeCount = DSL.field("employee_count", Integer.class);

    public DepartmentDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<DepartmentTableRow> fetchDepartmentTableData(DepartmentSearch search) {
        Range<Integer> employeeCountRange = search.getEmployeeCount();

        return dslContext.select(fields(
            prefixed(DEPARTMENT, COMPANY),
            head.NAME.as("department_head_name"),
            count(emp.ID).as(employeeCount))
        )
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.eq(COMPANY.ID))
            .leftJoin(EMPLOYEE.as(emp)).on(DEPARTMENT.ID.eq(emp.DEPARTMENT_ID))
            .leftJoin(EMPLOYEE.as(head)).on(head.ID.eq(DEPARTMENT.HEAD_ID))
            .where(head.NAME.containsIgnoreCase(search.getDepartmentHeadName()))
            .and(DEPARTMENT.NAME.containsIgnoreCase(search.getDepartmentName()))
            .and(COMPANY.NAME.containsIgnoreCase(search.getCompanyName()))
            .groupBy(DEPARTMENT.ID, COMPANY.ID, head.ID)
            .having(count(emp.ID).between(employeeCountRange.getMin(), employeeCountRange.getMax()))
            .fetchInto(DepartmentTableRow.class);
    }

    public List<DepartmentTableRow> fetchDepartmentTableData2(DepartmentSearch search) {
        return dslContext
            .select(fields(
                prefixed(DEPARTMENT, COMPANY),
                head.NAME.as("department_head_name"),
                count(emp.ID).as(employeeCount))
            )
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.eq(COMPANY.ID))
            .leftJoin(EMPLOYEE.as(emp)).on(DEPARTMENT.ID.eq(emp.DEPARTMENT_ID))
            .leftJoin(EMPLOYEE.as(head)).on(head.ID.eq(DEPARTMENT.HEAD_ID))
            .where(condition(head.NAME::containsIgnoreCase, search.getDepartmentHeadName())
                .and(DEPARTMENT.NAME::containsIgnoreCase, search.getDepartmentName())
                .and(COMPANY.NAME::containsIgnoreCase, search.getCompanyName())
                .build())
            .groupBy(DEPARTMENT.ID, COMPANY.ID, head.ID)
            .having(conditionBetween(count(emp.ID), search.getEmployeeCount()).build())
            .fetchInto(DepartmentTableRow.class);
    }



    public int fetchDepartmentTableCount() {
        return 0;
    }

}
