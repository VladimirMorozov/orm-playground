package me.vmorozov.orm.playground.jooq.domain;

public class DepartmentTableRow {

    private int id;
    private String departmentName;
    private String departmentHeadName;
    private int employeeCount;
    private String companyName;

    @Override
    public String toString() {
        return "DepartmentTableRow{" +
            "id=" + id +
            ", departmentName='" + departmentName + '\'' +
            ", departmentHeadName='" + departmentHeadName + '\'' +
            ", employeeCount=" + employeeCount +
            ", companyName='" + companyName + '\'' +
            '}';
    }

    public int getId() {
        return id;
    }

    public DepartmentTableRow setId(int id) {
        this.id = id;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public DepartmentTableRow setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getDepartmentHeadName() {
        return departmentHeadName;
    }

    public DepartmentTableRow setDepartmentHeadName(String departmentHeadName) {
        this.departmentHeadName = departmentHeadName;
        return this;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public DepartmentTableRow setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public DepartmentTableRow setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
}
