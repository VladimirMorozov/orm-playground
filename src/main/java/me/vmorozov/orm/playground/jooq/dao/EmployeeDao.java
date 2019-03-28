package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.domain.DepartmentInfo;
import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import me.vmorozov.orm.playground.jooq.generated.tables.records.EmployeeRecord;
import me.vmorozov.orm.playground.jooq.util.EpicMapperBuilder;
import org.jooq.DSLContext;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static me.vmorozov.orm.playground.jooq.generated.Tables.COMPANY;
import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.fields;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.mapToOptional;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixed;

@Repository
public class EmployeeDao {

    private DSLContext dslContext;
    private static final EmployeeTable E = EMPLOYEE;
    private static final DepartmentTable D = DEPARTMENT;

    private static final JdbcMapper<DepartmentInfo> departmentMapper = EpicMapperBuilder
        .forClass(DepartmentInfo.class)
        .build();

    public EmployeeDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Employee> findEmployees(int companyId) {
        return dslContext.select(COMPANY.asterisk())
            .from(EMPLOYEE)
            .join(DEPARTMENT).on(E.DEPARTMENT_ID.equal(D.ID))
            .where(DEPARTMENT.COMPANY_ID.equal(companyId))
            .fetchInto(Employee.class);
    }

    public EmployeeRecord findById(int id) {
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

    public void converToFromRecord(EmployeeRecord employeeRecord) {
        Employee employee = employeeRecord.into(Employee.class);
        EmployeeRecord newEmployeeRecord = dslContext.newRecord(EMPLOYEE, employee);
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
