package me.vmorozov.orm.playground.mybatis.dao;

import me.vmorozov.orm.playground.domain.DepartmentInfo;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    DepartmentInfo getDepartmentInfo(long id);

    List<DepartmentTableRow> getDepartmentTableList(
        @Param("s") DepartmentSearch departmentSearch,
        @Param("p") Pageable pageable);

}
