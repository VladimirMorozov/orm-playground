package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void shouldFetch() {
        List<Employee> employees = employeeDao.findEmployees(1);
        assertThat(employees)
            .extracting(Employee::getId)
            .containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

}