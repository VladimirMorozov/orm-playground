package me.vmorozov.orm.playground.mybatis.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.Range;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentMapperTest extends IntegrationTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    public void shouldFindInfo() {
        System.out.println(departmentMapper.getDepartmentInfo(1));
    }

    @Test
    public void shouldGetDepartmentTableList() {
        DepartmentSearch departmentSearch = new DepartmentSearch();
        departmentSearch.setDepartmentName("e")
            .setEmployeeCount(new Range<>(0, 1));
        System.out.println(departmentMapper.getDepartmentTableList(departmentSearch));
    }

}