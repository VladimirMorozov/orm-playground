package me.vmorozov.orm.playground.jooq.domain;

import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Company;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Department;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;

import java.util.List;

public class DepartmentInfo extends Department {

    private List<Employee> employees;
    private Company company;

    // getters/setters

}
