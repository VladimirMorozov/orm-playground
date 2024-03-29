package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.Range;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

public class DepartmentSearchDaoTest extends IntegrationTest {

    @Autowired
    DepartmentSearchDao departmentSearchDao;

    @Test
    public void run() {
        DepartmentSearch search = new DepartmentSearch();
        search.setEmployeeCount(new Range<>(0, 100));
        departmentSearchDao.fetchDepartmentTableData_Untemplated(search, PageRequest.of(0, 10, Sort.by("department_head_id")));
    }

    @Test
    public void shouldFetchTable() {
        DepartmentSearch departmentSearch = new DepartmentSearch()
            .setDepartmentHeadName("Vladimir")
            .setDepartmentName("e")
            .setCompanyName("garden")
            .setEmployeeCount(new Range<>(2, 3));

        DepartmentSearch emptyDepartmentSearch = new DepartmentSearch();

        List<DepartmentTableRow> departmentTableRows = departmentSearchDao.fetchDepartmentTableData(
            departmentSearch, PageRequest.of(0, 10, Sort.by(Direction.ASC, "departmentHeadName")));
        List<DepartmentTableRow> departmentTableRows2 = departmentSearchDao.fetchDepartmentTableData(departmentSearch, PageRequest.of(1, 2));
        departmentSearchDao.fetchDepartmentTableData(emptyDepartmentSearch, PageRequest.of(0, 100, Sort.by(Direction.ASC, "departmentHeadName")));

        int count = departmentSearchDao.fetchDepartmentTableCount(departmentSearch);
        int count2 = departmentSearchDao.fetchDepartmentTableCount(emptyDepartmentSearch);

        System.out.println(departmentTableRows);
        System.out.println(count);
        System.out.println(count2);
    }


}