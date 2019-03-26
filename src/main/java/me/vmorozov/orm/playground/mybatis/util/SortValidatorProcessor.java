package me.vmorozov.orm.playground.mybatis.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortValidatorProcessor {

    private Set<String> allowedFields = new HashSet<>();
    private List<Order> mandatoryFields = new ArrayList<>();
    private boolean convertPropertiesToSnakeCase = false;

    private static final String SNAKE_CASE_REGEX = "([a-z])([A-Z]+)";
    private static final String SNAKE_CASE_REPLACEMENT = "$1_$2";

    public SortValidatorProcessor allowSortByFieldsOf(Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            allowedFields.add(declaredField.getName());
        }
        return this;
    }

    public SortValidatorProcessor except(String property) {
        boolean isRemoved = allowedFields.remove(property);
        if (!isRemoved) {
            throw new RuntimeException("Could not exclude " + property +
                ". Allowed fields are " + allowedFields);
        }
        return this;
    }

    public SortValidatorProcessor alwaysSortBy(String property, Direction direction) {
        except(property);
        mandatoryFields.add(new Order(direction, property));
        return this;
    }

    public SortValidatorProcessor convertPropertiesToSnakeCase() {
        convertPropertiesToSnakeCase = true;
        return this;
    }

    public Pageable process(Pageable pageable) {
        Sort newSort = process(pageable.getSort());
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), newSort);
    }

    public Sort process(Sort sort) {
        validate(sort);

        Stream<Order> orderStream = sort.stream();
        if (convertPropertiesToSnakeCase) {
            orderStream = orderStream.map(o -> new Order(o.getDirection(), toSnakeCase(o.getProperty())));
        }
        List<Order> orders = Stream.concat(orderStream, mandatoryFields.stream())
            .collect(Collectors.toList());
        return Sort.by(orders);
    }

    private void validate(Sort sort) {
        sort.forEach(order -> {
            if (!allowedFields.contains(order.getProperty())) {
                throw new RuntimeException("forbidden field detected in sort parameters: " + order.getProperty());
            }
        });

    }

    private String toSnakeCase(String original) {
        return original
            .replaceAll(SNAKE_CASE_REGEX, SNAKE_CASE_REPLACEMENT)
            .toLowerCase();
    }

}
