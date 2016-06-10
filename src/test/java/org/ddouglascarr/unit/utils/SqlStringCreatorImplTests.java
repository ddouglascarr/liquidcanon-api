package org.ddouglascarr.unit.utils;

import org.ddouglascarr.utils.SqlStringCreator;
import org.junit.Test;
import org.ddouglascarr.utils.SqlStringCreatorImpl;

import static org.junit.Assert.*;


public class SqlStringCreatorImplTests
{
    TestClass testClass = new TestClass("a", "b", new Long(555));
    SqlStringCreator sqlStringCreator = new SqlStringCreatorImpl(testClass);

    @Test
    public void getColumnListShouldReturnSqlFormattedListWithoutPrefix() throws Exception
    {
        String sqlList = sqlStringCreator.getColumnList();
        assertEquals("foo_bar, id, long_property_name", sqlList);
    }

    @Test
    public void getColumnListShouldReturnSqlFormattedListWithPrefix() throws Exception
    {
        String sqlList = sqlStringCreator.prefix("m.").getColumnList();
        assertEquals("m.foo_bar, m.id, m.long_property_name", sqlList);
    }

    @Test
    public void getParameterListShouldReturnSqlFormattedListOfParameters() throws Exception
    {
        String sqlParameters = sqlStringCreator.getParameterList();
        assertEquals(":fooBar, :id, :longPropertyName", sqlParameters);
    }

    @Test
    public void excludeShouldExcludeParameters() throws Exception
    {
        String[] toExclude = {"id", "fooBar"};
        String sqlParameters = sqlStringCreator.exclude(toExclude).getParameterList();
        assertEquals(":longPropertyName", sqlParameters);
    }

    @Test
    public void aliasShouldSubstituteParameters() throws Exception
    {
        String sqlParameters = sqlStringCreator.alias("longPropertyName", "longerPropertyName")
                .getParameterList();
        assertEquals(":fooBar, :id, :longerPropertyName", sqlParameters);
    }

    @Test
    public void aliasShouldSubstituteColumns() throws Exception
    {
        String sqlColumns = sqlStringCreator.alias("longPropertyName", "longerPropertyName")
                .getColumnList();
        assertEquals("foo_bar, id, longer_property_name", sqlColumns);
    }

    @Test
    public void excludeAndPrefixShouldWorkTogether() throws Exception
    {
        String[] toExclude = {"longPropertyName"};
        String sqlList = sqlStringCreator.exclude(toExclude).prefix("m.")
                .getColumnList();
        assertEquals("m.foo_bar, m.id", sqlList);
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


