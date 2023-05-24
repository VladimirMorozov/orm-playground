package me.vmorozov.orm.playground.jpa.dao;

import me.vmorozov.orm.playground.domain.search.DepartmentSearch;
import me.vmorozov.orm.playground.domain.search.DepartmentTableRow;
import me.vmorozov.orm.playground.jpa.domain.Department;
import me.vmorozov.orm.playground.jpa.domain.Employee;
import me.vmorozov.orm.playground.jpa.util.OrderByBuilder;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.util.List;

import static me.vmorozov.orm.playground.jpa.util.PredicateBuilder.condition;

public class DepartmentSearchRepositoryImpl implements DepartmentSearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DepartmentTableRow> searchOld(DepartmentSearch search, Pageable pageable) {
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

        List<Order> orderByList = new OrderByBuilder()
            .withCriteriaBuilder(cb)
            .fromSort(pageable.getSort())
            .mapping("departmentName", departmentName)
            .mapping("departmentHeadName", departmentHeadName)
            .mapping("companyName", companyName)
            .mapping("employeeCount", employeeCount)
            .alwaysSortBy(cb.asc(id))
            .build();

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
                    .build(cb))
            .orderBy(orderByList);

        return em.createQuery(criteria)
            .setMaxResults(pageable.getPageSize())
            .setFirstResult((int) pageable.getOffset())
            .getResultList();

    }

    private Predicate containsIgnoreCase(Expression<String> expression, String parameter, CriteriaBuilder cb) {
        return cb.like(cb.lower(expression), '%'+parameter.toLowerCase()+'%');
    }

}
