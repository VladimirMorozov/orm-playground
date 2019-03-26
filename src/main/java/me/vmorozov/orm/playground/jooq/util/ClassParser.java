package me.vmorozov.orm.playground.jooq.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClassParser {

    private static final SnakeCaseStrategy SNAKE_CASE_STRATEGY = new SnakeCaseStrategy();

    private List<String> domainPackageNames;

    public ClassParser(List<String> domainPackageNames) {
        this.domainPackageNames = domainPackageNames;
    }

    public List<MappedProperty> parse(Class clazz, String name, String aliasName) {
        List<MappedProperty> result = new ArrayList<>();
        for (PropertyDescriptor propertyDescriptor : BeanUtils.getPropertyDescriptors(clazz)) {
            String packageName = propertyDescriptor.getPropertyType().getPackageName();
            boolean isDomainClass = domainPackageNames.stream().anyMatch(packageName::startsWith);
            if (isDomainClass) {
                System.out.println("domain");
                result.addAll(parse(propertyDescriptor.getPropertyType(), propertyDescriptor.getName(), null));
            } else if (Collection.class.isAssignableFrom(propertyDescriptor.getPropertyType())) {
                try {
                    Field field = clazz.getDeclaredField(propertyDescriptor.getName());
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    Class listElementClass = (Class) genericType.getActualTypeArguments()[0];

                    String fieldName = field.getName();
                    result.addAll(parse(listElementClass, fieldName.substring(0, fieldName.length()-1), fieldName));
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            } else if (!propertyDescriptor.getName().equals("class")) {
                System.out.println("field" + propertyDescriptor.getName());

                boolean isKey = propertyDescriptor.getName().equals("id");

                if (aliasName != null) {
                    result.add(new MappedProperty(
                        getPropertyName(name, propertyDescriptor),
                        SNAKE_CASE_STRATEGY.translate(aliasName) + "_" + SNAKE_CASE_STRATEGY.translate(propertyDescriptor.getName()),
                        isKey));
                } else {
                    result.add(new MappedProperty(
                        getPropertyName(name, propertyDescriptor),
                        null,
                        isKey));
                }

            }
        }
        return result;
    }

    private String getPropertyName(String name, PropertyDescriptor propertyDescriptor) {
        if (name != null) {
            return SNAKE_CASE_STRATEGY.translate(name) + "_" + SNAKE_CASE_STRATEGY.translate(propertyDescriptor.getName());
        } else {
            return SNAKE_CASE_STRATEGY.translate(propertyDescriptor.getName());
        }

    }

}

class MappedProperty {

    private String snakeCaseName;
    private String snakeCaseAlias;
    private boolean isKey;

    public MappedProperty(String snakeCaseName, String snakeCaseAlias, boolean isKey) {
        this.snakeCaseName = snakeCaseName;
        this.snakeCaseAlias = snakeCaseAlias;
        this.isKey = isKey;
    }

    @Override
    public String toString() {
        return "MappedProperty{" +
            "snakeCaseName='" + snakeCaseName + '\'' +
            ", snakeCaseAlias='" + snakeCaseAlias + '\'' +
            ", isKey=" + isKey +
            '}';
    }

    public String getSnakeCaseName() {
        return snakeCaseName;
    }

    public MappedProperty setSnakeCaseName(String snakeCaseName) {
        this.snakeCaseName = snakeCaseName;
        return this;
    }

    public String getSnakeCaseAlias() {
        return snakeCaseAlias;
    }

    public MappedProperty setSnakeCaseAlias(String snakeCaseAlias) {
        this.snakeCaseAlias = snakeCaseAlias;
        return this;
    }

    public boolean isKey() {
        return isKey;
    }

    public MappedProperty setKey(boolean key) {
        isKey = key;
        return this;
    }
}
