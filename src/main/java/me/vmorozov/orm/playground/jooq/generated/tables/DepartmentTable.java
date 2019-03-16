/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated.tables;

import me.vmorozov.orm.playground.jooq.generated.Indexes;
import me.vmorozov.orm.playground.jooq.generated.Keys;
import me.vmorozov.orm.playground.jooq.generated.Public;
import me.vmorozov.orm.playground.jooq.generated.tables.records.DepartmentRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
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
public class DepartmentTable extends TableImpl<DepartmentRecord> {

    private static final long serialVersionUID = -1782152875;

    /**
     * The reference instance of <code>public.department</code>
     */
    public static final DepartmentTable DEPARTMENT = new DepartmentTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DepartmentRecord> getRecordType() {
        return DepartmentRecord.class;
    }

    /**
     * The column <code>public.department.id</code>.
     */
    public final TableField<DepartmentRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('department_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.department.head_id</code>.
     */
    public final TableField<DepartmentRecord, Integer> HEAD_ID = createField("head_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.department.company_id</code>.
     */
    public final TableField<DepartmentRecord, Integer> COMPANY_ID = createField("company_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.department.name</code>.
     */
    public final TableField<DepartmentRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>public.department</code> table reference
     */
    public DepartmentTable() {
        this(DSL.name("department"), null);
    }

    /**
     * Create an aliased <code>public.department</code> table reference
     */
    public DepartmentTable(String alias) {
        this(DSL.name(alias), DEPARTMENT);
    }

    /**
     * Create an aliased <code>public.department</code> table reference
     */
    public DepartmentTable(Name alias) {
        this(alias, DEPARTMENT);
    }

    private DepartmentTable(Name alias, Table<DepartmentRecord> aliased) {
        this(alias, aliased, null);
    }

    private DepartmentTable(Name alias, Table<DepartmentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> DepartmentTable(Table<O> child, ForeignKey<O, DepartmentRecord> key) {
        super(child, key, DEPARTMENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DEPARTMENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DepartmentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DEPARTMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DepartmentRecord> getPrimaryKey() {
        return Keys.DEPARTMENT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DepartmentRecord>> getKeys() {
        return Arrays.<UniqueKey<DepartmentRecord>>asList(Keys.DEPARTMENT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<DepartmentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DepartmentRecord, ?>>asList(Keys.DEPARTMENT__DEPARTMENT_HEAD_ID_FKEY, Keys.DEPARTMENT__DEPARTMENT_COMPANY_ID_FKEY);
    }

    public EmployeeTable employee() {
        return new EmployeeTable(this, Keys.DEPARTMENT__DEPARTMENT_HEAD_ID_FKEY);
    }

    public CompanyTable company() {
        return new CompanyTable(this, Keys.DEPARTMENT__DEPARTMENT_COMPANY_ID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentTable as(String alias) {
        return new DepartmentTable(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentTable as(Name alias) {
        return new DepartmentTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DepartmentTable rename(String name) {
        return new DepartmentTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DepartmentTable rename(Name name) {
        return new DepartmentTable(name, null);
    }
}