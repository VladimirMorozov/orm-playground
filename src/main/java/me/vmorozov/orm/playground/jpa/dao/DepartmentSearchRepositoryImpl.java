package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.domain.DepartmentTableRow;
import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.jpa.domain.Department;
import me.vmorozov.orm.playground.jpa.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;

import static me.vmorozov.orm.playground.jpa.util.ConditionBuilder.condition;

public class DepartmentSearchRepositoryImpl implements DepartmentSearchRepository {

    @PersistenceContext
    private EntityManager em;

    public List<DepartmentTableRow> search(DepartmentSearch search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DepartmentTableRow> criteria = cb.createQuery(DepartmentTableRow.class);
        Root<Department> root = criteria.from(Department.class);

        Path<Long> id = root.get("id");
        Path<String> departmentName = root.get("name");
        Path<Long> companyId = root.join("company").get("id");
        Path<String> companyName = root.join("company").get("name");
        Path<String> departmentHeadName = root.join("head").get("name");
        Expression<Long> employeeCount = cb.count(root.join("employees"));

        Subquery<Employee> subquery = criteria.subquery(Employee.class);
        Root<Employee> employeeRoot = subquery.from(Employee.class);
        Path<String> employeePosition = employeeRoot.get("position");
        Path<Department> employeeDepartment = employeeRoot.get("department");

        criteria
            .select(cb.construct(DepartmentTableRow.class,
                id,
                departmentName,
                companyName,
                departmentHeadName,
                employeeCount))
            .where(
                condition(departmentHeadName, this::containsIgnoreCase, search.getDepartmentHeadName())
                    .and(departmentName, this::containsIgnoreCase, search.getDepartmentName())
                    .and(companyName, this::containsIgnoreCase, search.getCompanyName())
                    .and(cb.exists(
                        subquery
                            .select(employeeRoot)
                            .where(cb.equal(employeeDepartment, root),
                                cb.equal(employeePosition, "programmer"))),
                        search.isMustHaveProgrammers())
                    .build(cb))
            .groupBy(id, companyId, companyName, departmentHeadName)
            .having(
                condition(employeeCount, cb::ge, (long) search.getEmployeeCount().getMin())
                    .and(employeeCount, cb::le, (long) search.getEmployeeCount().getMax())
                    .build(cb));

        return em.createQuery(criteria).getResultList();

    }

    private Predicate containsIgnoreCase(Expression<String> expression, String parameter, CriteriaBuilder cb) {
        return cb.like(cb.lower(expression), '%'+parameter.toLowerCase()+'%');
    }

}
