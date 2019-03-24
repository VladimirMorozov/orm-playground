package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;

import java.util.List;

public interface DepartmentSearchRepository {

    public List<DepartmentTableRow> search(DepartmentSearch search);

}
