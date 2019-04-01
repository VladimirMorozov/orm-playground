package me.vmorozov.orm.playground.jpa.service;

import me.vmorozov.orm.playground.jpa.dao.DepartmentRepository;
import me.vmorozov.orm.playground.jpa.domain.Company;
import me.vmorozov.orm.playground.jpa.domain.Department;
import me.vmorozov.orm.playground.jpa.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JpaDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void demonstrateMagic() {
        Department department = departmentRepository.findById(1).orElseThrow();
        Company company = department.getCompany();
        company.setName("ICEFIRE");
    }

    public long countProgrammers() {
        Department department = departmentRepository.findById(1).orElseThrow();
        return department.getEmployees().stream()
            .filter(emp -> emp.getPosition().equals("programmer"))
            .count();
    }

    public void changeDepartment(Employee employee) {
        Department newDepartment = departmentRepository.getOne(1);
        employee.setDepartment(newDepartment);
    }

}
