package me.vmorozov.orm.playground.domain;

import java.util.List;

public class DepartmentInfo extends Department {

    private List<Employee> employees;
    private Company company;

    @Override
    public Department setCompanyId(Integer companyId) {
        if (company == null) {
            company = new Company();
        }
        company.setId(companyId);
        return super.setCompanyId(companyId);
    }

    @Override
    public String toString() {
        return "DepartmentInfo{" +
            "employees=" + employees +
            ", company=" + company +
            "} " + super.toString();
    }

    // getters/setters

    public List<Employee> getEmployees() {
        return employees;
    }

    public DepartmentInfo setEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public DepartmentInfo setCompany(Company company) {
        this.company = company;
        return this;
    }
}
