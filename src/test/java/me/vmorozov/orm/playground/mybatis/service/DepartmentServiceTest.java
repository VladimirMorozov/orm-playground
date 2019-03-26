package me.vmorozov.orm.playground.mybatis.service;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.Range;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DepartmentServiceTest extends IntegrationTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void shouldGetDepartmentTableList() {
        DepartmentSearch departmentSearch = new DepartmentSearch();
        departmentSearch.setDepartmentName("e")
            .setEmployeeCount(new Range<>(0, 10));
        Pageable pageable = PageRequest.of(0, 10, Sort.by("departmentName"));
        System.out.println(departmentService.getDepartmentTableList(departmentSearch, pageable));
    }

}