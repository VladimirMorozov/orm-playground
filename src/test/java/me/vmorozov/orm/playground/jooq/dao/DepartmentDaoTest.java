package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jooq.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.jooq.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.jooq.domain.search.Range;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentDaoTest extends IntegrationTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void shouldFetchTable() {
        DepartmentSearch departmentSearch = new DepartmentSearch()
            .setDepartmentHeadName("Vladimir")
            .setDepartmentName("e")
            .setCompanyName("garden")
            .setEmployeeCount(new Range<>(0, 10));

        DepartmentSearch emptyDepartmentSearch = new DepartmentSearch();

        List<DepartmentTableRow> departmentTableRows = departmentDao.fetchDepartmentTableData2(departmentSearch);
        departmentDao.fetchDepartmentTableData2(emptyDepartmentSearch);
        System.out.println(departmentTableRows);
    }


}