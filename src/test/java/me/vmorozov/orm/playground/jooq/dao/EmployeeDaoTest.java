package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import me.vmorozov.orm.playground.jooq.generated.tables.records.EmployeeRecord;
import org.jooq.DSLContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static org.assertj.core.api.Assertions.assertThat;


public class EmployeeDaoTest extends IntegrationTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void shouldFetch() {
        List<Employee> employees = employeeDao.findEmployees(1);
        String s = employeeDao.fetchName(1);
        assertThat(employees)
            .extracting(Employee::getId)
            .containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

    public void temp(DSLContext dslContext) {
        List<Employee> employees2 = employeeDao.findEmployees(2);
        employeeDao.findById(1);


        EmployeeRecord employeeRecord = employeeDao.findById(2);
        employeeRecord.setName("New name");
        employeeRecord.update();

        Employee employee = employeeRecord.into(Employee.class);
        EmployeeRecord newEmployeeRecord = dslContext.newRecord(EMPLOYEE, employee);

        newEmployeeRecord.getPosition();

        String s = employeeDao.fetchName(1);

    }

}