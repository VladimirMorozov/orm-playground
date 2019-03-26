/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jooq.generated.tables.pojos;


import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
public class Employee implements Serializable {

    private static final long serialVersionUID = -959474094;

    private Integer        id;
    private Integer        departmentId;
    private String         name;
    private String         position;
    private OffsetDateTime workStart;

    public Employee() {}

    public Employee(Employee value) {
        this.id = value.id;
        this.departmentId = value.departmentId;
        this.name = value.name;
        this.position = value.position;
        this.workStart = value.workStart;
    }

    public Employee(
        Integer        id,
        Integer        departmentId,
        String         name,
        String         position,
        OffsetDateTime workStart
    ) {
        this.id = id;
        this.departmentId = departmentId;
        this.name = name;
        this.position = position;
        this.workStart = workStart;
    }

    public Integer getId() {
        return this.id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    @NotNull
    public Integer getDepartmentId() {
        return this.departmentId;
    }

    public Employee setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getPosition() {
        return this.position;
    }

    public Employee setPosition(String position) {
        this.position = position;
        return this;
    }

    public OffsetDateTime getWorkStart() {
        return this.workStart;
    }

    public Employee setWorkStart(OffsetDateTime workStart) {
        this.workStart = workStart;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Employee (");

        sb.append(id);
        sb.append(", ").append(departmentId);
        sb.append(", ").append(name);
        sb.append(", ").append(position);
        sb.append(", ").append(workStart);

        sb.append(")");
        return sb.toString();
    }
}
