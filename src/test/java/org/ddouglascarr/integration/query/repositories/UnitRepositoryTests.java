package org.ddouglascarr.integration.query.repositories;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.repositories.UnitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/test-world.sql"
        })
public class UnitRepositoryTests
{
    @Autowired
    private UnitRepository unitRepository;

    @Test
    public void findOneById() throws Exception
    {
        Unit unit = unitRepository.findOneById(EARTH_MOON_FEDERATION_UNIT_ID);
        assertEquals(EARTH_MOON_FEDERATION_UNIT_ID, unit.getId());
    }
}
