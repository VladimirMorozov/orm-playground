package me.vmorozov.orm.playground.jooq.domain.search;

public class DepartmentSearch {

    private String departmentName;
    private String departmentHeadName;
    private Range<Integer> employeeCount;
    private String companyName;
    private boolean mustHaveProgrammers;

    public String getDepartmentName() {
        return departmentName;
    }

    public DepartmentSearch setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getDepartmentHeadName() {
        return departmentHeadName;
    }

    public DepartmentSearch setDepartmentHeadName(String departmentHeadName) {
        this.departmentHeadName = departmentHeadName;
        return this;
    }

    public Range<Integer> getEmployeeCount() {
        return employeeCount;
    }

    public DepartmentSearch setEmployeeCount(Range<Integer> employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public DepartmentSearch setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public boolean isMustHaveProgrammers() {
        return mustHaveProgrammers;
    }

    public DepartmentSearch setMustHaveProgrammers(boolean mustHaveProgrammers) {
        this.mustHaveProgrammers = mustHaveProgrammers;
        return this;
    }
}
