package me.vmorozov.orm.playground.jpa.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jpa.domain.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentRepositoryTest extends IntegrationTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetInfo() throws JsonProcessingException {
        Department info = departmentRepository.getInfo(1);
        System.out.println(objectMapper.writeValueAsString(info));
    }

}