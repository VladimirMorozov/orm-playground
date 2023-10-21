package me.vmorozov.orm.playground.jdbc_template;

import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.stream.Stream;

public class CombinedSqlParameterSource extends AbstractSqlParameterSource {

  private MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
  private List<BeanPropertySqlParameterSource> beanPropertySqlParameterSources;

  public CombinedSqlParameterSource(Object... objects) {
    this.beanPropertySqlParameterSources = Stream.of(objects)
            .map(BeanPropertySqlParameterSource::new)
            .toList();
  }

  public CombinedSqlParameterSource setValue(String paramName, Object value) {
    mapSqlParameterSource.addValue(paramName, value);
    return this;
  }

  @Override
  public boolean hasValue(String paramName) {
    return mapSqlParameterSource.hasValue(paramName) || beanPropertySqlParameterSources.stream().anyMatch(bs -> bs.hasValue(paramName)) ;
  }

  @Override
  public Object getValue(String paramName) {
    return mapSqlParameterSource.hasValue(paramName)
            ? mapSqlParameterSource.getValue(paramName)
            : beanPropertySqlParameterSources.stream()
            .filter(bs -> bs.hasValue(paramName))
            .map(bs -> bs.getValue(paramName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No parameter by name of " + paramName));
  }

  @Override
  public int getSqlType(String paramName) {
    return mapSqlParameterSource.hasValue(paramName)
            ? mapSqlParameterSource.getSqlType(paramName) :
            beanPropertySqlParameterSources.stream()
                    .filter(bs -> bs.hasValue(paramName))
                    .map(bs -> bs.getSqlType(paramName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No parameter by name of " + paramName));
  }

}
