package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;

@Repository
public class EmployeeDao {

    private DSLContext dslContext;

    public EmployeeDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Employee> findEmployees(int companyId) {
        return dslContext.select(EMPLOYEE.asterisk())
            .from(EMPLOYEE)
            .join(DEPARTMENT).on(EMPLOYEE.DEPARTMENT_ID.equal(DEPARTMENT.ID))
            .where(DEPARTMENT.COMPANY_ID.equal(companyId))
            .fetchInto(Employee.class);
    }

}
