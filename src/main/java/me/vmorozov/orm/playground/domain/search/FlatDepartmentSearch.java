package me.vmorozov.orm.playground.domain.search;

import lombok.Data;

@Data
public class FlatDepartmentSearch {

    private String departmentName;
    private String departmentHeadName;
    private Integer maxEmployeeCount;
    private Integer minEmployeeCount;
    private String companyName;
    private boolean mustHaveProgrammers;

    public static FlatDepartmentSearch fromDepartmentSearch(DepartmentSearch departmentSearch) {
        FlatDepartmentSearch result = new FlatDepartmentSearch();
        result.departmentName = departmentSearch.getDepartmentName();
        result.departmentHeadName = departmentSearch.getDepartmentHeadName();
        result.maxEmployeeCount = departmentSearch.getEmployeeCount() != null ? departmentSearch.getEmployeeCount().getMax() : null;
        result.minEmployeeCount = departmentSearch.getEmployeeCount() != null ? departmentSearch.getEmployeeCount().getMin() : null;
        result.companyName = departmentSearch.getCompanyName();
        result.mustHaveProgrammers = departmentSearch.isMustHaveProgrammers();
        return result;
    }

}
