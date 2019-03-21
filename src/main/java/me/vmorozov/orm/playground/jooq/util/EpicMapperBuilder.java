package me.vmorozov.orm.playground.jooq.util;

import org.simpleflatmapper.jdbc.JdbcMapper;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EpicMapperBuilder<T> {

    private ClassParser classParser = new ClassParser(Arrays.asList(
        "me.vmorozov.orm.playground.jooq.domain",
        "me.vmorozov.orm.playground.jooq.generated.tables.pojos")
    );

    private Class<T> clazz;
    private String rootName;

    public static <T> EpicMapperBuilder<T> forClass(Class<T> clazz) {
        return new EpicMapperBuilder<>(clazz);
    }

    private EpicMapperBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    public EpicMapperBuilder<T> withRootName(String rootName) {
        this.rootName = rootName;
        return this;
    }

    public JdbcMapper<T> build() {
        List<MappedProperty> parsed = classParser.parse(clazz, rootName, null);

        String[] keys = parsed.stream()
            .filter(MappedProperty::isKey)
            .map(MappedProperty::getSnakeCaseName)
            .toArray(String[]::new);

        Map<String, String> aliases = parsed.stream()
            .filter(p -> !Objects.isNull(p.getSnakeCaseAlias()))
            .collect(Collectors.toMap(MappedProperty::getSnakeCaseName, MappedProperty::getSnakeCaseAlias));

        return JdbcMapperFactory
            .newInstance()
            .unorderedJoin()
            .addKeys(keys) // todo auto create from object??? maybe create both sorting and selected fields...
            .addAliases(aliases)
            .newMapper(clazz);
    }
}
