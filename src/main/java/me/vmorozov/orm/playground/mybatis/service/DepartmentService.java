package me.vmorozov.orm.playground.mybatis.service;

import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.DepartmentUiTable;
import me.vmorozov.orm.playground.mybatis.dao.DepartmentMapper;
import me.vmorozov.orm.playground.mybatis.util.SortValidatorProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    private static final SortValidatorProcessor sortValidator = new SortValidatorProcessor()
        .allowSortByFieldsOf(DepartmentTableRow.class)
        .alwaysSortBy("id", Direction.ASC)
        .convertPropertiesToSnakeCase();

    private List<DepartmentTableRow> getDepartmentTableList(DepartmentSearch search, Pageable pageable) {
        return departmentMapper.getDepartmentTableList(search, sortValidator.process(pageable));
    }

    public DepartmentUiTable getTableData(DepartmentSearch search, Pageable pageable) {
        List<DepartmentTableRow> departmentTableList = getDepartmentTableList(search, pageable);
        long filteredCount = departmentMapper.getDepartmentTableCount(search);
        long totalCount = departmentMapper.getDepartmentTableCount(new DepartmentSearch());

        return new DepartmentUiTable(departmentTableList, filteredCount, totalCount);
    }

}

