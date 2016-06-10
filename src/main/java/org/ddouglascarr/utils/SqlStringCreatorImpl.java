package org.ddouglascarr.utils;

import com.google.common.base.CaseFormat;
import org.pure4j.collections.IPersistentMap;
import org.pure4j.collections.PersistentHashMap;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlStringCreatorImpl implements SqlStringCreator
{
    private final BeanPropertySqlParameterSource beanPropertySqlParameterSource;
    private final String prefix;
    private final List<String> exclude;
    private final IPersistentMap<String, String> aliases;

    public SqlStringCreatorImpl(Object entity)
    {
        this.beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(entity);
        this.prefix = "";
        this.exclude = new ArrayList<>();
        this.aliases = new PersistentHashMap<>();
    }

    private SqlStringCreatorImpl(BeanPropertySqlParameterSource beanPropertySqlParameterSource,
                                 String prefix, List<String> exclude,
                                 IPersistentMap<String, String> aliases)
    {
        this.beanPropertySqlParameterSource = beanPropertySqlParameterSource;
        this.prefix = prefix;
        this.exclude = exclude;
        this.aliases = aliases;
    }

    @Override
    public String getInsertColumns()
    {
        List<String> columns = createColumnsStream()
                .map(c -> mapInsertAlias(c))
                .map(c -> prefix.concat(c))
                .collect(Collectors.toList());
        return "(" + String.join(", ", columns) + ")";
    }

    public String getSelectColumns()
    {
        List<String> columns = createColumnsStream()
                .map(c -> mapSelectAlias(c))
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
                prefix, exclude, aliases);
    }

    @Override
    public SqlStringCreator exclude(String[] parameters)
    {
        return new SqlStringCreatorImpl(beanPropertySqlParameterSource,
                prefix, Arrays.asList(parameters), aliases);
    }

    @Override
    public SqlStringCreator alias(String key, String value)
    {
        return new SqlStringCreatorImpl(beanPropertySqlParameterSource,
                prefix, exclude, aliases.assoc(key, value));
    }

    private Stream<String> createColumnsStream()
    {
        return Arrays.stream(
                beanPropertySqlParameterSource.getReadablePropertyNames())
                .filter(c -> !c.equals("class"))
                .filter(c -> !exclude.contains(c))
                .map(c -> toSnakeCase(c));
    }

    private String toSnakeCase(String s)
    {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    private String toCamelCase(String s)
    {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
    }

    public SqlStringCreatorImpl clone() throws CloneNotSupportedException
    {
        return (SqlStringCreatorImpl) super.clone();
    }

    private String mapSelectAlias(String parameter)
    {
        if (aliases.containsKey(toCamelCase(parameter))) {
            return toSnakeCase(aliases.get(toCamelCase(parameter))) + " AS " + parameter;
        }
        return parameter;
    }

    private String mapInsertAlias(String parameter)
    {
        if (aliases.containsKey(toCamelCase(parameter))) {
            return toSnakeCase(aliases.get(toCamelCase(parameter)));
        }
        return parameter;
    }

}
