package org.ddouglascarr.utils;

import com.google.common.base.CaseFormat;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqlStringBuilderImpl implements SqlStringBuilder
{
    private BeanPropertySqlParameterSource beanPropertySqlParameterSource;

    public SqlStringBuilderImpl(Object entity)
    {
        this.beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(entity);
    }

    @Override
    public BeanPropertySqlParameterSource getBeanPropertySqlParameterSource()
    {
        return beanPropertySqlParameterSource;
    }

    @Override
    public String getColumnList()
    {
        return getColumnList(new SqlStringBuilderColumnPrefix(null));
    }

    @Override
    public String getColumnList(SqlStringBuilderColumnPrefix prefix)
    {
        List<String> columns = Arrays.stream(
                beanPropertySqlParameterSource.getReadablePropertyNames())
                .filter(c -> !c.equals("class"))
                .map(c -> toSnakeCase(c))
                .map(c -> prefix.getPrefix().concat(c))
                .collect(Collectors.toList());
        return String.join(", ", columns);
    }

    private String toSnakeCase(String s)
    {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    @Override
    public String getParameterList()
    {
        return null;
    }
}
