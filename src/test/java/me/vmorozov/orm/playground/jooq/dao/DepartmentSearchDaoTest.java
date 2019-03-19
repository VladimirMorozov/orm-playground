package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jooq.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.jooq.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.jooq.domain.search.Range;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class DepartmentSearchDaoTest extends IntegrationTest {

    @Autowired
    DepartmentSearchDao departmentSearchDao;

    @Test
    public void shouldFetchTable() {
        DepartmentSearch departmentSearch = new DepartmentSearch()
            .setDepartmentHeadName("Vladimir")
            .setDepartmentName("e")
            .setCompanyName("garden")
            .setEmployeeCount(new Range<>(0, 10));

        DepartmentSearch emptyDepartmentSearch = new DepartmentSearch();

        List<DepartmentTableRow> departmentTableRows = departmentSearchDao.fetchDepartmentTableData3(departmentSearch, PageRequest.of(0, 10));
        List<DepartmentTableRow> departmentTableRows2 = departmentSearchDao.fetchDepartmentTableData3(departmentSearch, PageRequest.of(1, 2));
        departmentSearchDao.fetchDepartmentTableData3(emptyDepartmentSearch, PageRequest.of(0, 100));

        int count = departmentSearchDao.fetchDepartmentTableCount(departmentSearch);
        int count2 = departmentSearchDao.fetchDepartmentTableCount(emptyDepartmentSearch);

        System.out.println(departmentTableRows);
        System.out.println(count);
        System.out.println(count2);
    }


}