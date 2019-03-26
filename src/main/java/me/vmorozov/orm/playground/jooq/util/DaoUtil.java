package me.vmorozov.orm.playground.jooq.util;

import org.apache.commons.lang3.ArrayUtils;
import org.jooq.Field;
import org.jooq.SelectFieldOrAsterisk;
import org.jooq.impl.TableImpl;
import org.simpleflatmapper.jdbc.JdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DaoUtil {

    private static Logger logger = LoggerFactory.getLogger(DaoUtil.class);

    private DaoUtil() {}

    public static SelectFieldOrAsterisk[] fields(SelectFieldOrAsterisk[] prefixed, SelectFieldOrAsterisk... selectFieldOrAsterisk) {
        return ArrayUtils.addAll(prefixed, selectFieldOrAsterisk);
    }

    public static Field[] fields(Field[] prefixed, Field[] subPrefixed) {
        return ArrayUtils.addAll(prefixed, subPrefixed);
    }

    public static SelectFieldOrAsterisk[] prefixedWithoutIds(TableImpl... tables) {
        List<Field> result = new ArrayList<>();
        for (TableImpl table : tables) {
            for (Field field : table.fields()) {
                if (!field.getName().endsWith("id")) {
                    result.add(field.as(table.getName() + "_" + field.getName()));
                }
            }
        }
        SelectFieldOrAsterisk[] array = new SelectFieldOrAsterisk[result.size()];
        return result.toArray(array);
    }

    public static SelectFieldOrAsterisk[] prefixed(TableImpl... tables) {
        List<Field> result = new ArrayList<>();
        for (TableImpl table : tables) {
            for (Field field : table.fields()) {
                result.add(field.as(table.getName() + "_" + field.getName()));
            }
        }
        SelectFieldOrAsterisk[] array = new SelectFieldOrAsterisk[result.size()];
        return result.toArray(array);
    }

    public static <T> List<T> mapToList(ResultSet resultSet, JdbcMapper<T> mapper) {
        try (resultSet) {
            List<T> result = mapper.stream(resultSet).collect(Collectors.toList());
            // can log result here...
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Optional<T> mapToOptional(ResultSet resultSet, JdbcMapper<T> mapper) {
        List<T> mapped = mapToList(resultSet, mapper);
        if (mapped.isEmpty()) {
            return Optional.empty();
        } else if (mapped.size() == 1) {
            return Optional.of(mapped.get(0));
        } else {
            throw new RuntimeException("Too many results");
        }
    }

    public static Map<String, String> pluralAliases(TableImpl... tables) {
        Map<String, String> aliases = new HashMap<>();
        for (TableImpl table : tables) {
            for (Field field : table.fields()) {
                aliases.put(table.getName() + "_" + field.getName(), table.getName() + "S_" + field.getName());
            }
        }
        return aliases;
    }

}
