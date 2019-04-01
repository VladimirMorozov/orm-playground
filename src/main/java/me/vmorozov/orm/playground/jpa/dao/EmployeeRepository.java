package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.jpa.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



}
