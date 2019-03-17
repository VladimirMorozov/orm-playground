package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import me.vmorozov.orm.playground.jooq.generated.tables.records.EmployeeRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;


@Repository
public class EmployeeDao {

    private DSLContext dslContext;
    private static final EmployeeTable E = EMPLOYEE;
    private static final DepartmentTable D = DEPARTMENT;

    public EmployeeDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    List<Employee> findEmployees(int companyId) {
        return dslContext.select(EMPLOYEE.asterisk())
            .from(EMPLOYEE)
            .join(DEPARTMENT).on(E.DEPARTMENT_ID.equal(D.ID))
            .where(DEPARTMENT.COMPANY_ID.equal(companyId))
            .fetchInto(Employee.class);
    }

    List<Employee> findEmployeesExtended(int companyId) {
        return dslContext.select(EMPLOYEE.asterisk(), DEPARTMENT.asterisk())
            .from(EMPLOYEE)
            .join(DEPARTMENT).on(E.DEPARTMENT_ID.equal(D.ID))
            .where(DEPARTMENT.COMPANY_ID.equal(companyId))
            .fetchInto(Employee.class);
    }

    EmployeeRecord findById(int id) {
        return dslContext.selectFrom(EMPLOYEE)
            .where(EMPLOYEE.ID.equal(id))
            .fetchOne();
    }

    String fetchName(int id) {
        return dslContext.select(EMPLOYEE.NAME)
            .from(EMPLOYEE)
            .where(EMPLOYEE.ID.equal(id))
            .fetchOne(EMPLOYEE.NAME);
    }

    public List<Employee> findEmployeesAliases(int companyId) {
        return dslContext.select(E.asterisk())
            .from(EMPLOYEE)
            .join(DEPARTMENT).on(E.DEPARTMENT_ID.equal(D.ID))
            .where(DEPARTMENT.COMPANY_ID.equal(companyId))
            .fetchInto(Employee.class);
    }

}
