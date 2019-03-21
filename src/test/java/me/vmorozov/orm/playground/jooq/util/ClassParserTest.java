package me.vmorozov.orm.playground.jooq.util;

import me.vmorozov.orm.playground.jooq.domain.DepartmentInfo;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ClassParserTest {

    @Test
    public void shouldParse() {
        ClassParser classParser = new ClassParser(Arrays.asList(
            "me.vmorozov.orm.playground.jooq.domain",
            "me.vmorozov.orm.playground.jooq.generated.tables.pojos"));
        List<MappedProperty> parse = classParser.parse(DepartmentInfo.class, "department", null);
        System.out.println(parse);
    }

}