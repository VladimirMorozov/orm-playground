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
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import javax.annotation.Generated;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<CompanyRecord, Integer> IDENTITY_COMPANY = Identities0.IDENTITY_COMPANY;
    public static final Identity<DepartmentRecord, Integer> IDENTITY_DEPARTMENT = Identities0.IDENTITY_DEPARTMENT;
    public static final Identity<EmployeeRecord, Integer> IDENTITY_EMPLOYEE = Identities0.IDENTITY_EMPLOYEE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<CompanyRecord> COMPANY_PKEY = UniqueKeys0.COMPANY_PKEY;
    public static final UniqueKey<DepartmentRecord> DEPARTMENT_PKEY = UniqueKeys0.DEPARTMENT_PKEY;
    public static final UniqueKey<EmployeeRecord> EMPLOYEE_PKEY = UniqueKeys0.EMPLOYEE_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<DepartmentRecord, EmployeeRecord> DEPARTMENT__DEPARTMENT_HEAD_ID_FKEY = ForeignKeys0.DEPARTMENT__DEPARTMENT_HEAD_ID_FKEY;
    public static final ForeignKey<DepartmentRecord, CompanyRecord> DEPARTMENT__DEPARTMENT_COMPANY_ID_FKEY = ForeignKeys0.DEPARTMENT__DEPARTMENT_COMPANY_ID_FKEY;
    public static final ForeignKey<EmployeeRecord, DepartmentRecord> EMPLOYEE__EMPLOYEE_DEPARTMENT_ID_FK = ForeignKeys0.EMPLOYEE__EMPLOYEE_DEPARTMENT_ID_FK;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<CompanyRecord, Integer> IDENTITY_COMPANY = Internal.createIdentity(CompanyTable.COMPANY, CompanyTable.COMPANY.ID);
        public static Identity<DepartmentRecord, Integer> IDENTITY_DEPARTMENT = Internal.createIdentity(DepartmentTable.DEPARTMENT, DepartmentTable.DEPARTMENT.ID);
        public static Identity<EmployeeRecord, Integer> IDENTITY_EMPLOYEE = Internal.createIdentity(EmployeeTable.EMPLOYEE, EmployeeTable.EMPLOYEE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<CompanyRecord> COMPANY_PKEY = Internal.createUniqueKey(CompanyTable.COMPANY, "company_pkey", CompanyTable.COMPANY.ID);
        public static final UniqueKey<DepartmentRecord> DEPARTMENT_PKEY = Internal.createUniqueKey(DepartmentTable.DEPARTMENT, "department_pkey", DepartmentTable.DEPARTMENT.ID);
        public static final UniqueKey<EmployeeRecord> EMPLOYEE_PKEY = Internal.createUniqueKey(EmployeeTable.EMPLOYEE, "employee_pkey", EmployeeTable.EMPLOYEE.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<DepartmentRecord, EmployeeRecord> DEPARTMENT__DEPARTMENT_HEAD_ID_FKEY = Internal.createForeignKey(me.vmorozov.orm.playground.jooq.generated.Keys.EMPLOYEE_PKEY, DepartmentTable.DEPARTMENT, "department__department_head_id_fkey", DepartmentTable.DEPARTMENT.HEAD_ID);
        public static final ForeignKey<DepartmentRecord, CompanyRecord> DEPARTMENT__DEPARTMENT_COMPANY_ID_FKEY = Internal.createForeignKey(me.vmorozov.orm.playground.jooq.generated.Keys.COMPANY_PKEY, DepartmentTable.DEPARTMENT, "department__department_company_id_fkey", DepartmentTable.DEPARTMENT.COMPANY_ID);
        public static final ForeignKey<EmployeeRecord, DepartmentRecord> EMPLOYEE__EMPLOYEE_DEPARTMENT_ID_FK = Internal.createForeignKey(me.vmorozov.orm.playground.jooq.generated.Keys.DEPARTMENT_PKEY, EmployeeTable.EMPLOYEE, "employee__employee_department_id_fk", EmployeeTable.EMPLOYEE.DEPARTMENT_ID);
    }
}
