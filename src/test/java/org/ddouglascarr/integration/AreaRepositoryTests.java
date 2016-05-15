package org.ddouglascarr.integration;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.repositories.AreaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        }
)
public class AreaRepositoryTests
{
    @Autowired
    private AreaRepository areaRepository;

    private final Long EARTH_UNIT_ID = new Long(2);
    private final Long MARS_UNIT_ID = new Long(5);
    private final Long NON_EXISTANT_AREA_ID = new Long(9999);
    private final Long MARS_STATUTES_AREA_ID = new Long(3);
    private final Long MARS_MINERAL_RESOURCES_AREA_ID = new Long(17);
    private final Long EARTH_STATUTES_AREA_ID = new Long(2);
    private final Long EARTH_SPACE_VEHICLES_AREA_ID = new Long(13);

    @Test(expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfDoesNotExist() throws Exception
    {
        areaRepository.findOne(NON_EXISTANT_AREA_ID);
    }

    @Test
    public void findOneShouldReturnArea() throws Exception
    {
        Area area = areaRepository.findOne(MARS_STATUTES_AREA_ID);
        assertEquals(MARS_STATUTES_AREA_ID, area.getId());
    }

    @Test
    public void findByUnitIdShouldReturnListOfAreasForEarth() throws Exception
    {
        List<Area> areas = areaRepository.findByUnitId(EARTH_UNIT_ID);
        assertEquals(3, areas.size());

        Area statutesArea = areas.stream()
                .filter(a -> EARTH_STATUTES_AREA_ID.equals(a.getId()))
                .findFirst().get();
        assertNotNull(statutesArea);
        assertEquals(EARTH_STATUTES_AREA_ID, statutesArea.getId());

        Area spaceVehiclesArea = areas.stream()
                .filter(a -> EARTH_SPACE_VEHICLES_AREA_ID.equals(a.getId()))
                .findFirst().get();
        assertNotNull(spaceVehiclesArea);
    }

    @Test
    public void findByUnitIdShouldReturnListOfAreasForMars() throws Exception
    {
        List<Area> areas = areaRepository.findByUnitId(MARS_UNIT_ID);
        assertEquals(4, areas.size());

        Area mineralResourcesArea = areas.stream()
                .filter(a -> MARS_MINERAL_RESOURCES_AREA_ID.equals(a.getId()))
                .findFirst().get();
        assertNotNull(mineralResourcesArea);

        Area statutesArea = areas.stream()
                .filter(a -> MARS_STATUTES_AREA_ID.equals(a.getId()))
                .findFirst().get();
        assertNotNull(statutesArea);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByUnitIdAndIdShouldThrowIfNotPartOfUnit() throws Exception
    {
        areaRepository.findOneByUnitIdAndId(EARTH_UNIT_ID, MARS_STATUTES_AREA_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByUnitIdAndIDSHouldThrowIfDoesNotExist() throws Exception
    {
        areaRepository.findOneByUnitIdAndId(EARTH_UNIT_ID, NON_EXISTANT_AREA_ID);
    }

    @Test
    public void findOneByUnitIdAndIDShouldReturnArea() throws Exception
    {
        Area area = areaRepository.findOneByUnitIdAndId(EARTH_UNIT_ID, EARTH_STATUTES_AREA_ID);
        assertNotNull(area);
        assertEquals(EARTH_STATUTES_AREA_ID, area.getId());
    }




}