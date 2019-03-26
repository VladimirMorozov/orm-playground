/*
 * This file is generated by jOOQ.
 */
package me.vmorozov.orm.playground.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

@Entity
@JsonIdentityInfo(property = "id", generator = PropertyGenerator.class)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  name;
    private String  position;
    private OffsetDateTime workStart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    public Employee() {}

    public Integer getId() {
        return id;
    }

    public Employee setId(Integer id) {
        this.id = id;
        return this;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Employee setPosition(String position) {
        this.position = position;
        return this;
    }

    public OffsetDateTime getWorkStart() {
        return workStart;
    }

    public Employee setWorkStart(OffsetDateTime workStart) {
        this.workStart = workStart;
        return this;
    }
}
