package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.IntegrationTest;
import me.vmorozov.orm.playground.jooq.domain.DepartmentTableRow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentDaoTest extends IntegrationTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void shouldFetchTable() {
        List<DepartmentTableRow> departmentTableRows = departmentDao.fetchDepartmentTableData();
        System.out.println(departmentTableRows);
    }


}