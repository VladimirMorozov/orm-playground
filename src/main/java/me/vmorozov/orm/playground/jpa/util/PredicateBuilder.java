package me.vmorozov.orm.playground.jpa.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class PredicateBuilder {

    private List<Predicate> conditions = new ArrayList<>();

    // save these as functions to add criteria builder in the end
    private List<Function<CriteriaBuilder, Predicate>> conditionProducers = new ArrayList<>();

    public static <T, P extends Expression<?>> PredicateBuilder condition(P path, BiFunction<P, T, Predicate> conditionProducer, T parameter) {
        return new PredicateBuilder().and(path, conditionProducer, parameter);
    }

    public static <T, P extends Expression<?>> PredicateBuilder condition(P path, TriFunction<P, T, CriteriaBuilder, Predicate> conditionProducer, T parameter) {
        return new PredicateBuilder().and(path, conditionProducer, parameter);
    }

    public <T, P extends Expression<?>> PredicateBuilder and(P path, BiFunction<P, T, Predicate> conditionProducer, T parameter) {
        if (parameter != null) {
            conditions.add(conditionProducer.apply(path, parameter));
        }
        return this;
    }

    public <T, P extends Expression<?>> PredicateBuilder and(P path, TriFunction<P, T, CriteriaBuilder, Predicate> conditionProducer, T parameter) {
        if (parameter != null) {
            conditionProducers.add(cb -> conditionProducer.apply(path, parameter, cb));
        }
        return this;
    }


    /**
     * @param condition condition to add
     * @param conditionToAddWhereCondition if false, condition won't be added
     * @return chaining PredicateBuilder
     */
    public PredicateBuilder and(Predicate condition, boolean conditionToAddWhereCondition) {
        if (conditionToAddWhereCondition) {
            conditions.add(condition);
        }
        return this;
    }

    public Predicate[] build(CriteriaBuilder cb) {
        Stream<Predicate> resolvedConditions = conditionProducers.stream()
            .map(cp -> cp.apply(cb));

        return Stream.concat(conditions.stream(), resolvedConditions)
            .toArray(Predicate[]::new);
    }

}
