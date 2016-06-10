package org.ddouglascarr.utils;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

public interface SqlStringCreator extends Cloneable
{
    String getColumnList();
    String getParameterList();

    SqlStringCreator prefix(String prefix);
    SqlStringCreator exclude(String[] parameters);

}
