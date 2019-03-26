/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated.tables.records;

import me.vmorozov.orm.playground.jooq.generated.tables.EmployeeTable;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


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
public class EmployeeRecord extends UpdatableRecordImpl<EmployeeRecord> implements Record5<Integer, Integer, String, String, OffsetDateTime> {

    private static final long serialVersionUID = 78853998;

    /**
     * Setter for <code>public.employee.id</code>.
     */
    public EmployeeRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.employee.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.employee.department_id</code>.
     */
    public EmployeeRecord setDepartmentId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.employee.department_id</code>.
     */
    @NotNull
    public Integer getDepartmentId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.employee.name</code>.
     */
    public EmployeeRecord setName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.employee.name</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.employee.position</code>.
     */
    public EmployeeRecord setPosition(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.employee.position</code>.
     */
    public String getPosition() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.employee.work_start</code>.
     */
    public EmployeeRecord setWorkStart(OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.employee.work_start</code>.
     */
    public OffsetDateTime getWorkStart() {
        return (OffsetDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, String, String, OffsetDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, String, String, OffsetDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return EmployeeTable.EMPLOYEE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return EmployeeTable.EMPLOYEE.DEPARTMENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return EmployeeTable.EMPLOYEE.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return EmployeeTable.EMPLOYEE.POSITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<OffsetDateTime> field5() {
        return EmployeeTable.EMPLOYEE.WORK_START;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getDepartmentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime component5() {
        return getWorkStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getDepartmentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime value5() {
        return getWorkStart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeRecord value2(Integer value) {
        setDepartmentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeRecord value3(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeRecord value4(String value) {
        setPosition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeRecord value5(OffsetDateTime value) {
        setWorkStart(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeeRecord values(Integer value1, Integer value2, String value3, String value4, OffsetDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EmployeeRecord
     */
    public EmployeeRecord() {
        super(EmployeeTable.EMPLOYEE);
    }

    /**
     * Create a detached, initialised EmployeeRecord
     */
    public EmployeeRecord(Integer id, Integer departmentId, String name, String position, OffsetDateTime workStart) {
        super(EmployeeTable.EMPLOYEE);

        set(0, id);
        set(1, departmentId);
        set(2, name);
        set(3, position);
        set(4, workStart);
    }
}
