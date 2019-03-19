package me.vmorozov.orm.playground.jooq.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import org.jooq.OrderField;
import org.jooq.SortField;
import org.jooq.SortOrder;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderByBuilder {

    private static final SnakeCaseStrategy SNAKE_CASE_STRATEGY = new SnakeCaseStrategy();

    private Set<String> allowedFields = new HashSet<>();
    private List<SortField<?>> mandatoryFields = new ArrayList<>();

    public OrderByBuilder allowSortByFieldsOf(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            allowedFields.add(declaredField.getName());
        }
        return this;
    }

    public OrderByBuilder except(org.jooq.Field<?> field) {
        boolean isRemoved = allowedFields.remove(field.getName());
        if (!isRemoved) {
            throw new RuntimeException("Could not exclude " + field.getName() +
                ". Allowed fields are " + allowedFields);
        }
        return this;
    }

    public OrderByBuilder alwaysSortBy(org.jooq.Field field, SortOrder sortOrder) {
        except(field);
        mandatoryFields.add(field.sort(sortOrder));
        return this;
    }

    public List<? extends OrderField<?>> build(Sort sort) {
        validate(sort);
        List<SortField<?>> list = sort.stream().map(order -> {
            String snakeCaseName = SNAKE_CASE_STRATEGY.translate(order.getProperty());
            org.jooq.Field<?> field = DSL.field(snakeCaseName);
            switch (order.getDirection()) {
                case ASC:
                    return field.asc();
                case DESC:
                    return field.desc();
            }
            throw new RuntimeException("Unexpected sort direction");
        }).collect(Collectors.toList());

        list.addAll(mandatoryFields);
        return list;
    }

    private void validate(Sort sort) {
        sort.forEach(order -> {
            if (!allowedFields.contains(order.getProperty())) {
                throw new RuntimeException("forbidden field detected in sort parameters: " + order.getProperty());
            }
        });

    }

}
