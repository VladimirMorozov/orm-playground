package me.vmorozov.orm.playground.jpa.service;

import me.vmorozov.orm.playground.jpa.dao.DepartmentRepository;
import me.vmorozov.orm.playground.jpa.domain.Company;
import me.vmorozov.orm.playground.jpa.domain.Department;
import me.vmorozov.orm.playground.jpa.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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

    public void doDifferentChanges() {
        Department department = departmentRepository.getReferenceById(3);
        Company company = department.getCompany();

        // these changes will apply automatically
        department.setName("Empty*"  + new Random().nextInt(100));
        company.setName("Socks Ltd.*" + new Random().nextInt(100));

        department.getEmployees().remove(0);
        department.getEmployees().add(new Employee().setName("Freddy").setPosition("High Priest").setDepartment(department));

    }

}
