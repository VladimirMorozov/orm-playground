package me.vmorozov.orm.playground.jooq.util;

import org.apache.commons.lang3.ArrayUtils;
import org.jooq.Field;
import org.jooq.SelectFieldOrAsterisk;
import org.jooq.impl.TableImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DaoUtil {

    private static Logger logger = LoggerFactory.getLogger(DaoUtil.class);

    private DaoUtil() {}

    public static SelectFieldOrAsterisk[] fields(Field[] prefixed, SelectFieldOrAsterisk... selectFieldOrAsterisk) {
        return ArrayUtils.addAll(prefixed, selectFieldOrAsterisk);
    }

    public static Field[] fields(Field[] prefixed, Field[] subPrefixed) {
        return ArrayUtils.addAll(prefixed, subPrefixed);
    }

    public static Field[] prefixed(TableImpl... tables) {
        List<Field> result = new ArrayList<>();
        for (TableImpl table : tables) {
            for (Field field : table.fields()) {
                result.add(field.as(table.getName() + "_" + field.getName()));
            }
        }
        Field[] array = new Field[result.size()];
        return result.toArray(array);
    }

}
