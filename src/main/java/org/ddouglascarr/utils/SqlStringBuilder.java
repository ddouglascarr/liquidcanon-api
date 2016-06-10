package org.ddouglascarr.utils;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

public interface SqlStringBuilder
{
    BeanPropertySqlParameterSource getBeanPropertySqlParameterSource();
    String getColumnList();
    String getColumnList(SqlStringBuilderColumnPrefix prefix);
    String getParameterList();
}
