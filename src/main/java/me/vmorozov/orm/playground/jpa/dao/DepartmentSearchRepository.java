package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentSearchRepository {

    public List<DepartmentTableRow> search(DepartmentSearch search, Pageable pageable);

}
