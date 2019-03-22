package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.jpa.domain.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    @Query("select d, e, c " +
        "from Department as d " +
        "join fetch d.employees as e " +
        "join fetch d.company as c " +
        "where d.id = :id")
    public Department getInfo(@Param("id") int id);

}
