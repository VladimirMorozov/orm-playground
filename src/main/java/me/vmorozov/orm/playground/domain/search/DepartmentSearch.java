package me.vmorozov.orm.playground.domain.search;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DepartmentSearch {

    private String departmentName;
    private String departmentHeadName;
    private Range<Integer> employeeCount;
    private String companyName;
    private boolean mustHaveProgrammers;

}
