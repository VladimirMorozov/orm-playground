package me.vmorozov.orm.playground.jooq.dao;

import me.vmorozov.orm.playground.jooq.domain.DepartmentInfo;
import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Employee;
import me.vmorozov.orm.playground.jooq.util.EpicMapperBuilder;
import org.jooq.DSLContext;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static me.vmorozov.orm.playground.jooq.generated.Tables.COMPANY;
import static me.vmorozov.orm.playground.jooq.generated.Tables.DEPARTMENT;
import static me.vmorozov.orm.playground.jooq.generated.Tables.EMPLOYEE;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.fields;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.mapToOptional;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.pluralAliases;
import static me.vmorozov.orm.playground.jooq.util.DaoUtil.prefixed;

@Repository
public class EmployeeDao {

    private DSLContext dslContext;
    private static final EmployeeTable E = EMPLOYEE;
    private static final DepartmentTable D = DEPARTMENT;

    private static final JdbcMapper<DepartmentInfo> departmentMapper = JdbcMapperFactory
        .newInstance()
        .unorderedJoin()
        .addKeys("department_id", "employee_id", "company_id") // todo auto create from object??? maybe create both sorting and selected fields...
        .addAliases(pluralAliases(EMPLOYEE))
        .newMapper(DepartmentInfo.class);

    private static final JdbcMapper<DepartmentInfo> departmentMapper2 = EpicMapperBuilder
        .forClass(DepartmentInfo.class)
        .withRootName("department")
        .build();

    public EmployeeDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Employee> findEmployees(int companyId) {
        return dslContext.select(E.asterisk())
            .from(EMPLOYEE)
            .join(DEPARTMENT).on(E.DEPARTMENT_ID.equal(D.ID))
            .where(DEPARTMENT.COMPANY_ID.equal(companyId))
            .fetchInto(Employee.class);
    }

    public List<DepartmentInfo> getDepartmentInfo0(int departmentId) {
        return dslContext.select(EMPLOYEE.asterisk(), DEPARTMENT.asterisk(), COMPANY.asterisk())
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.equal(COMPANY.ID))
            .join(EMPLOYEE).on(DEPARTMENT.ID.eq(EMPLOYEE.DEPARTMENT_ID))
            .where(DEPARTMENT.ID.equal(departmentId))
            .fetchInto(DepartmentInfo.class);
    }

    public Optional<DepartmentInfo> getDepartmentInfo(int departmentId) {
        dslContext.select(fields(prefixed(EMPLOYEE, COMPANY), DEPARTMENT.asterisk()))
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.equal(COMPANY.ID))
            .join(EMPLOYEE).on(DEPARTMENT.ID.eq(EMPLOYEE.DEPARTMENT_ID))
            .where(DEPARTMENT.ID.equal(departmentId))
            .fetch();

        ResultSet resultSet = dslContext.select(fields(prefixed(EMPLOYEE, COMPANY), DEPARTMENT.asterisk()))
            .from(DEPARTMENT)
            .join(COMPANY).on(DEPARTMENT.COMPANY_ID.equal(COMPANY.ID))
            .join(EMPLOYEE).on(DEPARTMENT.ID.eq(EMPLOYEE.DEPARTMENT_ID))
            .where(DEPARTMENT.ID.equal(departmentId))
            .fetchResultSet();

        return mapToOptional(resultSet, departmentMapper2);
    }

}
