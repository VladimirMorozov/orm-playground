package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.jpa.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends /* ... */ JpaRepository<Department, Integer>, DepartmentSearchRepository {

    @Query("select d " +
        "from Department as d " +
        "join fetch d.employees as e " +
        "join fetch d.company as c " +
        "where d.id = :id")
    Department getInfo(@Param("id") int id);

}
