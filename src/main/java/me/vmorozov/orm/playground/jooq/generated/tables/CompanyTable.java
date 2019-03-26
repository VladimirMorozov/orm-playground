/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated.tables;

import me.vmorozov.orm.playground.jooq.generated.Indexes;
import me.vmorozov.orm.playground.jooq.generated.Keys;
import me.vmorozov.orm.playground.jooq.generated.Public;
import me.vmorozov.orm.playground.jooq.generated.tables.records.CompanyRecord;
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
public class CompanyTable extends TableImpl<CompanyRecord> {

    private static final long serialVersionUID = -152823719;

    /**
     * The reference instance of <code>public.company</code>
     */
    public static final CompanyTable COMPANY = new CompanyTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CompanyRecord> getRecordType() {
        return CompanyRecord.class;
    }

    /**
     * The column <code>public.company.id</code>.
     */
    public final TableField<CompanyRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('company_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.company.name</code>.
     */
    public final TableField<CompanyRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

    /**
     * Create a <code>public.company</code> table reference
     */
    public CompanyTable() {
        this(DSL.name("company"), null);
    }

    /**
     * Create an aliased <code>public.company</code> table reference
     */
    public CompanyTable(String alias) {
        this(DSL.name(alias), COMPANY);
    }

    /**
     * Create an aliased <code>public.company</code> table reference
     */
    public CompanyTable(Name alias) {
        this(alias, COMPANY);
    }

    private CompanyTable(Name alias, Table<CompanyRecord> aliased) {
        this(alias, aliased, null);
    }

    private CompanyTable(Name alias, Table<CompanyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> CompanyTable(Table<O> child, ForeignKey<O, CompanyRecord> key) {
        super(child, key, COMPANY);
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
        return Arrays.<Index>asList(Indexes.COMPANY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CompanyRecord, Integer> getIdentity() {
        return Keys.IDENTITY_COMPANY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CompanyRecord> getPrimaryKey() {
        return Keys.COMPANY_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CompanyRecord>> getKeys() {
        return Arrays.<UniqueKey<CompanyRecord>>asList(Keys.COMPANY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompanyTable as(String alias) {
        return new CompanyTable(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompanyTable as(Name alias) {
        return new CompanyTable(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CompanyTable rename(String name) {
        return new CompanyTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CompanyTable rename(Name name) {
        return new CompanyTable(name, null);
    }
}
