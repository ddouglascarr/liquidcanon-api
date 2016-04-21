package org.ddouglascarr.unit;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.repositories.AreaRepository;
import org.ddouglascarr.services.AreaService;
import org.ddouglascarr.services.AreaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AreaServiceImplTests
{
    @Mock
    private AreaRepository areaRepository;

    @InjectMocks
    private AreaService areaService = new AreaServiceImpl();

    private Area mockArea2;
    private Area mockArea3;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockArea2 = new Area();
        mockArea2.setId(new Long(2));
        mockArea2.setUnitId(new Long(12));

        mockArea3 = new Area();
        mockArea3.setId(new Long(3));
        mockArea2.setUnitId(new Long(12));
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfNotFound() throws ItemNotFoundException
    {
        when(areaRepository.findOne(new Long(6))).thenReturn(null);
        Area returnedArea = areaService.findOne(new Long(6));
    }

    @Test
    public void findOneShouldReturnArea() throws ItemNotFoundException
    {
        when(areaRepository.findOne(new Long(2))).thenReturn(mockArea2);
        Area returnedArea = areaService.findOne(new Long(2));
        assertEquals(returnedArea, mockArea2);
    }

    @Test
    public void findByUnitIdShouldReturnListOfAreas()
    {
        List<Area> mockList = new ArrayList<>();
        mockList.add(mockArea2);
        mockList.add(mockArea3);
        when(areaRepository.findByUnitId(new Long(12))).thenReturn(mockList);

        List<Area> returnedList = areaService.findByUnitId((new Long(12)));
        assertEquals(returnedList, mockList);
    }
}
