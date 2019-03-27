package me.vmorozov.orm.playground.domain.search;

import java.util.List;

public class DepartmentUiTable {

    private List<DepartmentTableRow> rows;
    private long filteredCount;
    private long totalCount;

    public DepartmentUiTable(List<DepartmentTableRow> rows, long filteredCount, long totalCount) {
        this.rows = rows;
        this.filteredCount = filteredCount;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "DepartmentUiTable{" +
            "rows=" + rows +
            ", filteredCount=" + filteredCount +
            ", totalCount=" + totalCount +
            '}';
    }

    public List<DepartmentTableRow> getRows() {
        return rows;
    }

    public DepartmentUiTable setRows(List<DepartmentTableRow> rows) {
        this.rows = rows;
        return this;
    }

    public long getFilteredCount() {
        return filteredCount;
    }

    public DepartmentUiTable setFilteredCount(long filteredCount) {
        this.filteredCount = filteredCount;
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public DepartmentUiTable setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }
}
