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
    public void getSelectColumnshouldReturnSqlFormattedListWithoutPrefix() throws Exception
    {
        String sqlList = sqlStringCreator.getSelectColumns();
        assertEquals("foo_bar, id, long_property_name", sqlList);
    }

    @Test
    public void getSelectColumnsShouldReturnSqlFormattedListWithPrefix() throws Exception
    {
        String sqlList = sqlStringCreator.prefix("m.").getSelectColumns();
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
    public void aliasShouldNotSubstituteParameters() throws Exception
    {
        String sqlParameters = sqlStringCreator.alias("longPropertyName", "longerPropertyName")
                .getParameterList();
        assertEquals(":fooBar, :id, :longPropertyName", sqlParameters);
    }

    @Test
    public void aliasShouldSubstituteSelectColumns() throws Exception
    {
        String sqlColumns = sqlStringCreator.alias("long_property_name", "longer_property_name")
                .getSelectColumns();
        assertEquals("foo_bar, id, longer_property_name AS long_property_name", sqlColumns);
    }

    @Test
    public void aliasShouldSubstituteInsertColumns() throws Exception
    {
        String sqlColumns = sqlStringCreator.alias("long_property_name", "longer_property_name")
                .exclude(new String[] {"fooBar", "id"}).getInsertColumns();
        assertEquals("(longer_property_name)", sqlColumns);
    }

    @Test
    public void aliasShouldNotEffectParameters() throws Exception
    {
        String sqlColumns = sqlStringCreator.alias("long_property_name", "longer_property_name")
                .exclude(new String[] {"fooBar", "id"}).getParameterList();
        assertEquals(":longPropertyName", sqlColumns);
    }
    @Test
    public void excludeAndPrefixShouldWorkTogether() throws Exception
    {
        String[] toExclude = {"longPropertyName"};
        String sqlList = sqlStringCreator.exclude(toExclude).prefix("m.")
                .getSelectColumns();
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


