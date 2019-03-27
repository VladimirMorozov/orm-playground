package me.vmorozov.orm.playground.mybatis.dao;

import me.vmorozov.orm.playground.domain.DepartmentInfo;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // not required by application, removes idea's warning
public interface DepartmentMapper {

    DepartmentInfo getDepartmentInfo(long id);

    List<DepartmentTableRow> getDepartmentTableList(
        @Param("s") DepartmentSearch departmentSearch,
        @Param("p") Pageable pageable);

    long getDepartmentTableCount(@Param("s") DepartmentSearch departmentSearch);


}
