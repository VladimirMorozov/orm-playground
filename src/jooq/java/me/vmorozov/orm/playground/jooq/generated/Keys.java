/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated;


import me.vmorozov.orm.playground.jooq.generated.tables.CompanyTable;
import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import me.vmorozov.orm.playground.jooq.generated.tables.records.CompanyRecord;
import me.vmorozov.orm.playground.jooq.generated.tables.records.DepartmentRecord;
import me.vmorozov.orm.playground.jooq.generated.tables.records.EmployeeRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CompanyRecord> COMPANY_PKEY = Internal.createUniqueKey(CompanyTable.COMPANY, DSL.name("company_pkey"), new TableField[] { CompanyTable.COMPANY.ID }, true);
    public static final UniqueKey<DepartmentRecord> DEPARTMENT_PKEY = Internal.createUniqueKey(DepartmentTable.DEPARTMENT, DSL.name("department_pkey"), new TableField[] { DepartmentTable.DEPARTMENT.ID }, true);
    public static final UniqueKey<EmployeeRecord> EMPLOYEE_PKEY = Internal.createUniqueKey(EmployeeTable.EMPLOYEE, DSL.name("employee_pkey"), new TableField[] { EmployeeTable.EMPLOYEE.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<DepartmentRecord, CompanyRecord> DEPARTMENT__DEPARTMENT_COMPANY_ID_FKEY = Internal.createForeignKey(DepartmentTable.DEPARTMENT, DSL.name("department_company_id_fkey"), new TableField[] { DepartmentTable.DEPARTMENT.COMPANY_ID }, Keys.COMPANY_PKEY, new TableField[] { CompanyTable.COMPANY.ID }, true);
    public static final ForeignKey<DepartmentRecord, EmployeeRecord> DEPARTMENT__DEPARTMENT_HEAD_ID_FKEY = Internal.createForeignKey(DepartmentTable.DEPARTMENT, DSL.name("department_head_id_fkey"), new TableField[] { DepartmentTable.DEPARTMENT.HEAD_ID }, Keys.EMPLOYEE_PKEY, new TableField[] { EmployeeTable.EMPLOYEE.ID }, true);
    public static final ForeignKey<EmployeeRecord, DepartmentRecord> EMPLOYEE__EMPLOYEE_DEPARTMENT_ID_FK = Internal.createForeignKey(EmployeeTable.EMPLOYEE, DSL.name("employee_department_id_fk"), new TableField[] { EmployeeTable.EMPLOYEE.DEPARTMENT_ID }, Keys.DEPARTMENT_PKEY, new TableField[] { DepartmentTable.DEPARTMENT.ID }, true);
}
