package me.vmorozov.orm.playground.jpa.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jpa.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EmployeeRepositoryTest extends IntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {
        Optional<Employee> employee = employeeRepository.findById(1);
        System.out.println(employee.get().getDepartment().getName());
        System.out.println(objectMapper.writeValueAsString(employee));
    }

}