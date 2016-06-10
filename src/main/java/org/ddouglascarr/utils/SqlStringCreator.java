package org.ddouglascarr.utils;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.util.Map;

public interface SqlStringCreator extends Cloneable
{
    String getColumnList();
    String getParameterList();

    SqlStringCreator prefix(String prefix);
    SqlStringCreator exclude(String[] parameters);
    SqlStringCreator alias(String key, String value);

}
