package me.vmorozov.orm.playground.jooq.generator;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.meta.TableDefinition;

public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {


    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        if (definition instanceof TableDefinition && mode == Mode.DEFAULT) {
            return super.getJavaClassName(definition, mode) + "Table";
        }
        return super.getJavaClassName(definition, mode);
    }


}
