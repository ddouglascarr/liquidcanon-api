package org.ddouglascarr.unit.controllers;

import org.ddouglascarr.controllers.AreasController;
import org.ddouglascarr.enums.ExceptionCodes;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.AreaService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AreasControllerTests
{
    @Mock
    private AreaService areaService;

    @Mock
    private UserDetailsImpl userDetails;

    @InjectMocks
    private AreasController areasController = new AreasController();

    private Area mockArea;
    private List<Area> mockAreaList;
    private static Long UNIT_ID = new Long(2);
    private static Long AREA_ID = new Long(12);
    private static Long MEMBER_ID = new Long(32);

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        when(userDetails.getId()).thenReturn(MEMBER_ID);
        mockArea = new Area();
        mockArea.setId(AREA_ID);
        mockAreaList = new ArrayList<>();
    }

    @Test
    public void getAreasShouldReturnListOfAreas() throws Exception
    {
        mockAreaList.add(mockArea);
        when(areaService.findByUnitId(MEMBER_ID, UNIT_ID)).thenReturn(mockAreaList);
        ResponseEntity<List<Area>> resp = areasController.getAreas(userDetails, UNIT_ID);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        List<Area> returnedList = resp.getBody();
        assertEquals(returnedList, mockAreaList);
    }

    @Test
    public void getAreaShould401IfNotUnitReadPrivileged() throws Exception
    {
        when(areaService.findOneByUnitId(MEMBER_ID, UNIT_ID, AREA_ID))
                .thenThrow(new MemberUnprivilegedException());
        ResponseEntity<Area> resp = areasController.getArea(userDetails, UNIT_ID, AREA_ID);
        assertEquals(HttpStatus.UNAUTHORIZED, resp.getStatusCode());
        assertEquals(resp.getHeaders().getFirst("error-code"),
                ExceptionCodes.UNPRIVILEGED.toString());
    }

    @Test
    public void getAreaShouldReturnArea() throws Exception
    {
        when(areaService.findOneByUnitId(MEMBER_ID, UNIT_ID, AREA_ID)).thenReturn(mockArea);

        ResponseEntity<Area> resp = areasController.getArea(userDetails, UNIT_ID, AREA_ID);
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertEquals(mockArea, resp.getBody());
    }

}
