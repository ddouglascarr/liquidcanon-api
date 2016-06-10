package org.ddouglascarr.utils;

public class SqlStringBuilderColumnPrefix
{
    private String prefix = "";

    public SqlStringBuilderColumnPrefix(String prefix)
    {
        if (null != prefix) {
            this.prefix = prefix;
        }
    }

    public String getPrefix()
    {
        return prefix;
    }

}
