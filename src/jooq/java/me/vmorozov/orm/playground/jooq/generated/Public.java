/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated;


import java.util.Arrays;
import java.util.List;

import me.vmorozov.orm.playground.jooq.generated.tables.CompanyTable;
import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.company</code>.
     */
    public final CompanyTable COMPANY = CompanyTable.COMPANY;

    /**
     * The table <code>public.department</code>.
     */
    public final DepartmentTable DEPARTMENT = DepartmentTable.DEPARTMENT;

    /**
     * The table <code>public.employee</code>.
     */
    public final EmployeeTable EMPLOYEE = EmployeeTable.EMPLOYEE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            CompanyTable.COMPANY,
            DepartmentTable.DEPARTMENT,
            EmployeeTable.EMPLOYEE
        );
    }
}
