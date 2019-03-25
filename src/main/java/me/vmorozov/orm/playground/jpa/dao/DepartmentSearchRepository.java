package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentSearchRepository {

    public List<DepartmentTableRow> searchOld(DepartmentSearch search, Pageable pageable);

}
