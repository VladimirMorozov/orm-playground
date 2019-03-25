package me.vmorozov.orm.playground.mybatis.dao;

import me.vmorozov.orm.playground.domain.DepartmentInfo;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentInfo getDepartmentInfo(long id);

    List<DepartmentTableRow> getDepartmentTableList(DepartmentSearch departmentSearch);

}
