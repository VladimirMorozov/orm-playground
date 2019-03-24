package me.vmorozov.orm.playground.jpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.Range;
import me.vmorozov.orm.playground.jpa.dao.DepartmentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentSearchServiceTest extends IntegrationTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSearch() throws JsonProcessingException {
        DepartmentSearch departmentSearch = new DepartmentSearch();
        departmentSearch.setDepartmentName("Green")
            .setEmployeeCount(new Range<>(0, 10))
            .setMustHaveProgrammers(true);
        List<DepartmentTableRow> result = departmentRepository.search(departmentSearch);
        System.out.println(objectMapper.writeValueAsString(result));
    }


}