package me.vmorozov.orm.playground.jdbc_template;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.Range;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

class DepartmentSearchJdbcDaoTest extends IntegrationTest {

    @Autowired
    DepartmentSearchJdbcDao departmentSearchJdbcDao;

    @Test
    void searches() {
        DepartmentSearch departmentSearch = new DepartmentSearch()
                .setDepartmentHeadName("Vladimir")
                .setDepartmentName("e")
                .setCompanyName("garden")
                .setEmployeeCount(new Range<>(2, 3));

        DepartmentSearch emptyDepartmentSearch = new DepartmentSearch();

        List<DepartmentTableRow> departmentTableRows = departmentSearchJdbcDao.fetchDepartmentTableData(
                departmentSearch, PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "departmentHeadName")));
        List<DepartmentTableRow> departmentTableRows2 = departmentSearchJdbcDao.fetchDepartmentTableData(departmentSearch, PageRequest.of(1, 2));
        departmentSearchJdbcDao.fetchDepartmentTableData(emptyDepartmentSearch, PageRequest.of(0, 100, Sort.by(Sort.Direction.ASC, "departmentHeadName")));

        long count = departmentSearchJdbcDao.fetchDepartmentTableCount(departmentSearch);
        long count2 = departmentSearchJdbcDao.fetchDepartmentTableCount(emptyDepartmentSearch);

        System.out.println(departmentTableRows);
        System.out.println(count);
        System.out.println(count2);
    }

}