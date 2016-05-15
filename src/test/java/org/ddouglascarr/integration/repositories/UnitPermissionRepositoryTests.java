package org.ddouglascarr.integration.repositories;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.UnitPermission;
import org.ddouglascarr.repositories.UnitPermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
                "classpath:sql/test-world.sql"
        })
public class UnitPermissionRepositoryTests
{
    @Autowired
    private UnitPermissionRepository unitPermissionRepository;

    private Long EARTH_UNIT_ID = new Long(2);
    private Long EARTH_UNIT_PERMISSION_ID = new Long(2);
    private Long NON_EXISTANT_ID = new Long(666);
    private Long SOLAR_SYSTEM_UNIT_PERMISSION_ID = new Long(1);

    @Test(expected = ItemNotFoundException.class)
    public void findOneByIdShouldThrowIfNotFound() throws Exception
    {
        unitPermissionRepository.findOne(NON_EXISTANT_ID);
    }

    @Test
    public void findOneByIdShouldReturnUnitPermission() throws Exception
    {
        UnitPermission unitPermission = unitPermissionRepository.findOne(SOLAR_SYSTEM_UNIT_PERMISSION_ID);
        assertEquals(SOLAR_SYSTEM_UNIT_PERMISSION_ID, unitPermission.getId());
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByUnitIdShouldThrowIfNotFound() throws Exception
    {
        unitPermissionRepository.findOneByUnitId(NON_EXISTANT_ID);
    }

    @Test
    public void findOneByUnitIdShouldReturnUnitPermission() throws Exception
    {
        UnitPermission unitPermission = unitPermissionRepository
                .findOneByUnitId(EARTH_UNIT_PERMISSION_ID);
        assertEquals(EARTH_UNIT_ID, unitPermission.getId());
    }
}
