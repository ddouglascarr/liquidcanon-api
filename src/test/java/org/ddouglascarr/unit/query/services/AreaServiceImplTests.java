package org.ddouglascarr.unit.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Area;
import org.ddouglascarr.query.repositories.AreaRepository;
import org.ddouglascarr.query.services.AreaService;
import org.ddouglascarr.query.services.AreaServiceImpl;
import org.ddouglascarr.query.services.PrivilegeService;
import org.ddouglascarr.query.services.UnitService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AreaServiceImplTests
{
    @Mock
    private AreaRepository areaRepository;

    @Mock
    PrivilegeService privilegeService;

    @Mock
    private UnitService unitService;

    @InjectMocks
    private AreaService areaService = new AreaServiceImpl();

    private Area mockArea2;
    private Area mockArea3;

    private static UUID AREA_2_ID = UUID.randomUUID();
    private static UUID AREA_3_ID = UUID.randomUUID();
    private static UUID UNIT_ID = UUID.randomUUID();
    private static UUID MEMBER_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockArea2 = new Area();
        mockArea2.setId(AREA_2_ID);
        mockArea2.setUnitId(UNIT_ID);

        mockArea3 = new Area();
        mockArea3.setId(AREA_3_ID);
        mockArea3.setUnitId(UNIT_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfNotFound() throws Exception
    {
        when(areaRepository.findOne(AREA_2_ID)).thenReturn(null);
        Area returnedArea = areaService.findOne(MEMBER_ID, AREA_2_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findOneShouldThrowIfNotReadPrivileged() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        when(areaRepository.findOne(AREA_2_ID)).thenReturn(mockArea2);
        areaService.findOne(MEMBER_ID, AREA_2_ID);
    }

    @Test
    public void findOneShouldReturnArea() throws Exception
    {
        when(areaRepository.findOne(AREA_2_ID)).thenReturn(mockArea2);
        Area returnedArea = areaService.findOne(MEMBER_ID, AREA_2_ID);
        assertEquals(returnedArea, mockArea2);
        verify(privilegeService, times(1)).assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByUnitIdShouldThrowIfNotFound() throws Exception
    {
        when(areaRepository.findOneByUnitIdAndId(UNIT_ID, AREA_2_ID)).thenReturn(null);
        areaService.findOneByUnitId(MEMBER_ID, UNIT_ID, AREA_2_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void findOneByUnitIdShouldThrowIfNotReadPrivileged() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        areaService.findOneByUnitId(MEMBER_ID, UNIT_ID, AREA_2_ID);
    }

    @Test
    public void findOneByUnitIdShouldReturnArea() throws Exception
    {
        when(areaRepository.findOneByUnitIdAndId(UNIT_ID, AREA_2_ID)).thenReturn(mockArea2);
        Area returnedArea = areaService.findOneByUnitId(MEMBER_ID, UNIT_ID, AREA_2_ID);
        assertEquals(mockArea2, returnedArea);
    }

    @Test( expected = MemberUnprivilegedException.class)
    public void findByUnitIdShould401IfNotReadPrivileged() throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
        areaService.findByUnitId(MEMBER_ID, UNIT_ID);
    }

    @Test( expected = ItemNotFoundException.class)
    public void findByUnitIdShould404IfUnitDoesNotExist() throws Exception
    {
        List<Area> emptyList = new ArrayList<>();
        when(areaRepository.findByUnitId(UNIT_ID)).thenReturn(emptyList);
        when(unitService.findOne(MEMBER_ID, UNIT_ID))
                .thenThrow(new ItemNotFoundException());
        areaService.findByUnitId(MEMBER_ID, UNIT_ID);
    }

    @Test
    public void findByUnitIdShouldReturnListOfAreas() throws Exception
    {
        List<Area> mockList = new ArrayList<>();
        mockList.add(mockArea2);
        mockList.add(mockArea3);
        when(areaRepository.findByUnitId(UNIT_ID)).thenReturn(mockList);

        List<Area> returnedList = areaService.findByUnitId(MEMBER_ID, UNIT_ID);
        assertEquals(returnedList, mockList);
        verify(privilegeService, times(1)).assertUnitReadPrivilege(MEMBER_ID, UNIT_ID);
    }
}
