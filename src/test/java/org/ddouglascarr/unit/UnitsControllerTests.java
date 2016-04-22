package org.ddouglascarr.unit;

import org.ddouglascarr.controllers.UnitsController;
import org.ddouglascarr.enums.ExceptionCodes;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.services.AreaService;
import org.ddouglascarr.services.MemberService;
import org.ddouglascarr.services.UnitService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitsControllerTests
{
    @Mock
    private UnitService unitService;

    @Mock
    private MemberService memberService;

    @Mock
    private AreaService areaService;

    @InjectMocks
    private UnitsController unitsController = new UnitsController();

    private Unit mockUnit2;
    private Unit mockUnit3;
    private List<Area> mockAreaList;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockUnit2 = new Unit();
        mockUnit2.setId(new Long(2));
        mockUnit2.setName("Mock Unit 2");

        mockUnit3 = new Unit();
        mockUnit3.setId(new Long(3));
        mockUnit3.setName("Mock Unit 3");

        Area mockArea12 = new Area();
        mockArea12.setId(new Long(12));
        Area mockArea13 = new Area();
        mockArea13.setId(new Long(13));
        mockAreaList = new ArrayList<>();
        mockAreaList.add(mockArea12);
        mockAreaList.add(mockArea13);
        mockUnit2.setAreas(mockAreaList);
    }

    @Test
    public void getUnitShouldReturn404IfNotFound() throws ItemNotFoundException
    {
        when(unitService.findOne(new Long(6))).thenThrow(new ItemNotFoundException());
        ResponseEntity<Unit> resp = unitsController.getUnit(new Long(6));
        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
        MultiValueMap<String, String> headers = resp.getHeaders();
        assertEquals(headers.getFirst("error-code"), ExceptionCodes.ITEM_NOT_FOUND.toString());
    }

    @Test
    public void getUnitShouldReturnUnit() throws ItemNotFoundException
    {
        when(unitService.findOne(new Long(2))).thenReturn(mockUnit2);
        ResponseEntity<Unit> resp = unitsController.getUnit(new Long(2));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        Unit returnedUnit = resp.getBody();
        assertEquals(returnedUnit, mockUnit2);
    }

    @Test
    public void getMembersShouldReturnMembersForUnit()
    {
        Member mockMember2 = new Member();
        mockMember2.setId(new Long(2));
        Member mockMember3 = new Member();
        mockMember3.setId(new Long(3));
        List<Member> mockList = new ArrayList<>();
        mockList.add(mockMember2);
        mockList.add(mockMember3);

        when(memberService.findByUnitId(new Long(2))).thenReturn(mockList);

        ResponseEntity<List<Member>> resp = unitsController.getMembers(new Long(2));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        List<Member> returnedList = resp.getBody();
        assertEquals(returnedList.size(), 2);
        assertEquals(returnedList.get(0), mockMember2);
        assertEquals(returnedList.get(1), mockMember3);
    }

    @Test
    public void getMembersShouldReturnEmptyListIfNoMembers()
    {
        List<Member> mockList = new ArrayList<>();
        when(memberService.findByUnitId(new Long(6))).thenReturn(mockList);

        ResponseEntity<List<Member>> resp = unitsController.getMembers(new Long(6));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        List<Member> returnedList = resp.getBody();
        assertEquals(returnedList.size(), 0);
    }

    @Test
    public void getAreasShouldReturnListOfAreas() throws ItemNotFoundException
    {
        when(unitService.findOne(new Long(2))).thenReturn(mockUnit2);

        ResponseEntity<List<Area>> resp = unitsController.getAreas(new Long(2));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        List<Area> returnedList = resp.getBody();
        assertEquals(returnedList, mockAreaList);
    }

    @Test
    public void getAreasShouldReturn404IfUnitDoesNotExist() throws ItemNotFoundException
    {
        when(unitService.findOne(new Long(6))).thenThrow(new ItemNotFoundException());
        ResponseEntity<List<Area>> resp = unitsController.getAreas(new Long(6));
        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(resp.getHeaders().getFirst("error-code"),
                ExceptionCodes.ITEM_NOT_FOUND.toString());

    }

    @Test
    public void getAreaShouldReturn404IfAreaDoesNotExist() throws ItemNotFoundException
    {
        when(areaService.findOneByUnitId(new Long(2), new Long(6))).thenThrow(new ItemNotFoundException());
        ResponseEntity<Area> resp = unitsController.getArea(new Long(2), new Long(6));
        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
        assertEquals(resp.getHeaders().getFirst("error-code"),
                ExceptionCodes.ITEM_NOT_FOUND.toString());
    }

    @Test
    public void getAreaShouldReturnArea() throws ItemNotFoundException
    {
        Area mockArea = new Area();
        mockArea.setId(new Long(12));
        mockArea.setUnitId(new Long(2));
        when(areaService.findOneByUnitId(new Long(2), new Long(12))).thenReturn(mockArea);

        ResponseEntity<Area> resp = unitsController.getArea(new Long(2), new Long(12));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        assertEquals(mockArea, resp.getBody());
    }
}
