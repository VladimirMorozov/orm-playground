package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class EmployeeDaoTest extends IntegrationTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void shouldFetch() {
        List<Employee> employees = employeeDao.findEmployees(1);
    }

}