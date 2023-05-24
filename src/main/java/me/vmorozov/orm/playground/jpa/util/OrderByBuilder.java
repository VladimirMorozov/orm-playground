package me.vmorozov.orm.playground.jpa.util;

import org.springframework.data.domain.Sort;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderByBuilder {

    private Map<String, Expression> sortMapping = new HashMap<>();
    private CriteriaBuilder cb;
    private Sort sort;

    private List<Order> alwaysSortBy = new ArrayList<>();

    public OrderByBuilder mapping(String sortParameter, Expression targetExpression) {
        sortMapping.put(sortParameter, targetExpression);
        return this;
    }

    public OrderByBuilder withCriteriaBuilder(CriteriaBuilder cb) {
        this.cb = cb;
        return this;
    }

    public OrderByBuilder fromSort(Sort sort) {
        this.sort = sort;
        return this;
    }

    public OrderByBuilder alwaysSortBy(Order order) {
        alwaysSortBy.add(order);
        return this;
    }

    public List<Order> build() {
        List<Order> result = sort.stream()
            .map(o -> {
                Expression sortedExpression = sortMapping.get(o.getProperty());
                if (sortedExpression == null) {
                    throw new RuntimeException("could not find expression for: " + o.getProperty());
                }
                switch (o.getDirection()) {
                    case DESC:
                        return cb.desc(sortedExpression);
                    case ASC:
                        return cb.asc(sortedExpression);
                }
                throw new RuntimeException("Unexpected sort direction");
            })
            .collect(Collectors.toList());

        result.addAll(alwaysSortBy);
        return result;
    }

}
