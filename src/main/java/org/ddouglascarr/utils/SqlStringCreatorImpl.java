package org.ddouglascarr.utils;

import com.google.common.base.CaseFormat;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqlStringCreatorImpl implements SqlStringCreator
{
    private final BeanPropertySqlParameterSource beanPropertySqlParameterSource;
    private final String prefix;
    private final List<String> exclude;

    public SqlStringCreatorImpl(Object entity)
    {
        this.beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(entity);
        this.prefix = "";
        this.exclude = new ArrayList<>();
    }

    private SqlStringCreatorImpl(BeanPropertySqlParameterSource beanPropertySqlParameterSource,
                                 String prefix, List<String> exclude)
    {
        this.beanPropertySqlParameterSource = beanPropertySqlParameterSource;
        this.prefix = prefix;
        this.exclude = exclude;
    }

    @Override
    public String getColumnList()
    {
        List<String> columns = Arrays.stream(
                beanPropertySqlParameterSource.getReadablePropertyNames())
                .filter(c -> !c.equals("class"))
                .filter(c -> !exclude.contains(c))
                .map(c -> toSnakeCase(c))
                .map(c -> prefix.concat(c))
                .collect(Collectors.toList());
        return String.join(", ", columns);
    }

    @Override
    public String getParameterList()
    {
        List<String> parameters = Arrays.stream(
                beanPropertySqlParameterSource.getReadablePropertyNames())
                .filter(p -> !p.equals("class"))
                .filter(p -> !exclude.contains(p))
                .map(p -> ":".concat(p))
                .collect(Collectors.toList());
        return String.join(", ", parameters);
    }

    @Override
    public SqlStringCreator prefix(String prefix)
    {
        return new SqlStringCreatorImpl(beanPropertySqlParameterSource,
                prefix, exclude);
    }

    @Override
    public SqlStringCreator exclude(String[] parameters)
    {
        return new SqlStringCreatorImpl(beanPropertySqlParameterSource,
                prefix, Arrays.asList(parameters));
    }

    private String toSnakeCase(String s)
    {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    public SqlStringCreatorImpl clone() throws CloneNotSupportedException
    {
        return (SqlStringCreatorImpl) super.clone();
    }

}
