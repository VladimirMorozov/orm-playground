/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated;

import me.vmorozov.orm.playground.jooq.generated.tables.CompanyTable;
import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1797997653;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.company</code>.
     */
    public final CompanyTable COMPANY = me.vmorozov.orm.playground.jooq.generated.tables.CompanyTable.COMPANY;

    /**
     * The table <code>public.department</code>.
     */
    public final DepartmentTable DEPARTMENT = me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable.DEPARTMENT;

    /**
     * The table <code>public.employee</code>.
     */
    public final EmployeeTable EMPLOYEE = me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable.EMPLOYEE;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.COMPANY_ID_SEQ,
            Sequences.DEPARTMENT_ID_SEQ,
            Sequences.EMPLOYEE_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            CompanyTable.COMPANY,
            DepartmentTable.DEPARTMENT,
            EmployeeTable.EMPLOYEE);
    }
}
