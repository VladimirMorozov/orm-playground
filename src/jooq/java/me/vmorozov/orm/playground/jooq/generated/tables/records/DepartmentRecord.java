/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated.tables.records;


import jakarta.validation.constraints.NotNull;

import me.vmorozov.orm.playground.jooq.generated.tables.DepartmentTable;
import me.vmorozov.orm.playground.jooq.generated.tables.pojos.Department;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DepartmentRecord extends UpdatableRecordImpl<DepartmentRecord> implements Record4<Integer, Integer, Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.department.id</code>.
     */
    public DepartmentRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.department.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.department.head_id</code>.
     */
    public DepartmentRecord setHeadId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.department.head_id</code>.
     */
    public Integer getHeadId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.department.company_id</code>.
     */
    public DepartmentRecord setCompanyId(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.department.company_id</code>.
     */
    @NotNull
    public Integer getCompanyId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.department.name</code>.
     */
    public DepartmentRecord setName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.department.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Integer, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return DepartmentTable.DEPARTMENT.ID;
    }

    @Override
    public Field<Integer> field2() {
        return DepartmentTable.DEPARTMENT.HEAD_ID;
    }

    @Override
    public Field<Integer> field3() {
        return DepartmentTable.DEPARTMENT.COMPANY_ID;
    }

    @Override
    public Field<String> field4() {
        return DepartmentTable.DEPARTMENT.NAME;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getHeadId();
    }

    @Override
    public Integer component3() {
        return getCompanyId();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getHeadId();
    }

    @Override
    public Integer value3() {
        return getCompanyId();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public DepartmentRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public DepartmentRecord value2(Integer value) {
        setHeadId(value);
        return this;
    }

    @Override
    public DepartmentRecord value3(Integer value) {
        setCompanyId(value);
        return this;
    }

    @Override
    public DepartmentRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public DepartmentRecord values(Integer value1, Integer value2, Integer value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DepartmentRecord
     */
    public DepartmentRecord() {
        super(DepartmentTable.DEPARTMENT);
    }

    /**
     * Create a detached, initialised DepartmentRecord
     */
    public DepartmentRecord(Integer id, Integer headId, Integer companyId, String name) {
        super(DepartmentTable.DEPARTMENT);

        setId(id);
        setHeadId(headId);
        setCompanyId(companyId);
        setName(name);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised DepartmentRecord
     */
    public DepartmentRecord(Department value) {
        super(DepartmentTable.DEPARTMENT);

        if (value != null) {
            setId(value.getId());
            setHeadId(value.getHeadId());
            setCompanyId(value.getCompanyId());
            setName(value.getName());
            resetChangedOnNotNull();
        }
    }
}
