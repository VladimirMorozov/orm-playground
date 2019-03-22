package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.jpa.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {



}
