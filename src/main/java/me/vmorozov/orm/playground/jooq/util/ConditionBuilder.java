package me.vmorozov.orm.playground.jooq.util;

import me.vmorozov.orm.playground.jooq.domain.search.Range;
import org.jooq.Condition;
import org.jooq.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConditionBuilder {

    private List<Condition> conditions = new ArrayList<>();

    public static <T> ConditionBuilder condition(Function<T, Condition> conditionProducer, T parameter) {
        return new ConditionBuilder().and(conditionProducer, parameter);
    }

    public static ConditionBuilder conditionBetween(Field<Integer> integerField, Range<Integer> parameter) {
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        if (parameter != null) {
            conditionBuilder.and(integerField::ge, parameter.getMin());
            conditionBuilder.and(integerField::le, parameter.getMax());
        }
        return conditionBuilder;
    }

    public <T> ConditionBuilder and(Function<T, Condition> conditionProducer, T parameter) {
        if (parameter != null) {
            conditions.add(conditionProducer.apply(parameter));
        }
        return this;
    }

    public ConditionBuilder and(Condition condition, boolean conditionToAddWhereCondition) {
        if (conditionToAddWhereCondition) {
            conditions.add(condition);
        }
        return this;
    }

    public List<Condition> build() {
        return conditions;
    }

}
