package org.ddouglascarr.integration;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.repositories.UnitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/liquid-canon.sql",
                "classpath:sql/test-users.sql",
                "classpath:sql/test-scenario-0.sql"
        })
public class UnitRepositoryTests
{
    @Autowired
    private UnitRepository unitRepository;

    @Before
    public void setup()
    {

    }

    @Test
    public void findOneById() throws Exception
    {
        Unit unit = unitRepository.findOneById(new Long(1));
        assertEquals(new Long(1), unit.getId());
    }
}
