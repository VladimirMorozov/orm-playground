package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.jooq.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.vmorozov.orm.playground.jooq.generated.Tables.COMPANY;
import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.fields;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixed;
import static org.jooq.impl.DSL.count;

@Repository
public class DepartmentDao {

    private DSLContext dslContext;
    private static final EmployeeTable head = EMPLOYEE.as("head");
    private static final EmployeeTable emp = EMPLOYEE.as("emp");

    public DepartmentDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<DepartmentTableRow> fetchDepartmentTableData() {
        return dslContext.select(fields(
            prefixed(DEPARTMENT, COMPANY),
            head.NAME.as("department_head_name"),
            count(emp.ID).as("employee_count"))
        )
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.eq(COMPANY.ID))
            .leftJoin(EMPLOYEE.as(emp)).on(DEPARTMENT.ID.eq(emp.DEPARTMENT_ID))
            .leftJoin(EMPLOYEE.as(head)).on(head.ID.eq(DEPARTMENT.HEAD_ID))
            .groupBy(DEPARTMENT.ID, COMPANY.ID, head.ID)
            .fetchInto(DepartmentTableRow.class);
    }

    public int fetchDepartmentTableCount() {
        return 0;
    }

}
