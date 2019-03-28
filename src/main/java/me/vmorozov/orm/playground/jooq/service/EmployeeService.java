package me.vmorozov.orm.playground.jooq.service;

import me.vmorozov.orm.playground.jooq.dao.EmployeeDao;
import me.vmorozov.orm.playground.jooq.generated.tables.records.EmployeeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public void changeRecord() {
        EmployeeRecord employeeRecord = employeeDao.findById(1);
        employeeRecord.setName("New Value");
        employeeRecord.update();
    }

}
