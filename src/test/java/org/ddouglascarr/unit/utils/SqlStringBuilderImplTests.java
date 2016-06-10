package org.ddouglascarr.unit.utils;

import org.ddouglascarr.utils.SqlStringBuilder;
import org.ddouglascarr.utils.SqlStringBuilderColumnPrefix;
import org.junit.Test;
import org.ddouglascarr.utils.SqlStringBuilderImpl;

import static org.junit.Assert.*;


public class SqlStringBuilderImplTests
{

    @Test
    public void getColumnListShouldReturnSqlFormattedListWithoutPrefix() throws Exception
    {
        TestClass testClass = new TestClass("a", "b", new Long(555));
        SqlStringBuilder sqlStringBuilder = new SqlStringBuilderImpl(testClass);
        String sqlList = sqlStringBuilder.getColumnList();
        assertEquals("foo_bar, id, long_property_name", sqlList);
    }

    @Test
    public void getColumnListShouldReturnSqlFormattedListWithPrefix() throws Exception
    {
        TestClass testClass = new TestClass("a", "b", new Long(555));
        SqlStringBuilder sqlStringBuilder = new SqlStringBuilderImpl(testClass);
        String sqlList = sqlStringBuilder.getColumnList(new SqlStringBuilderColumnPrefix("m."));
        assertEquals("m.foo_bar, m.id, m.long_property_name", sqlList);
    }

    private class TestClass
    {
        private String id;
        private String fooBar;
        private Long longPropertyName;

        public TestClass(String id, String fooBar, Long longPropertyName)
        {
            this.id = id;
            this.fooBar = fooBar;
            this.longPropertyName = longPropertyName;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getFooBar()
        {
            return fooBar;
        }

        public void setFooBar(String fooBar)
        {
            this.fooBar = fooBar;
        }

        public Long getLongPropertyName()
        {
            return longPropertyName;
        }

        public void setLongPropertyName(Long longPropertyName)
        {
            this.longPropertyName = longPropertyName;
        }
    }

}


