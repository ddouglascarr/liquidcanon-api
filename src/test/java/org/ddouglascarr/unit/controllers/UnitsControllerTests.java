package org.ddouglascarr.unit.controllers;

import org.ddouglascarr.controllers.UnitsController;
import org.ddouglascarr.enums.ExceptionCodes;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.models.UserDetailsImpl;
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
import java.util.UUID;

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

    @Mock
    private UserDetailsImpl userDetails;

    @InjectMocks
    private UnitsController unitsController = new UnitsController();

    private Unit mockUnit2;
    private Unit mockUnit3;
    private List<Area> mockAreaList;

    private static UUID UNIT_2_ID = UUID.randomUUID();
    private static UUID UNIT_3_ID = UUID.randomUUID();
    private static UUID AREA_12_ID = UUID.randomUUID();
    private static UUID AREA_13_ID = UUID.randomUUID();
    private static UUID MEMBER_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockUnit2 = new Unit();
        mockUnit2.setId(UNIT_2_ID);
        mockUnit2.setName("Mock Unit 2");

        mockUnit3 = new Unit();
        mockUnit3.setId(UNIT_3_ID);
        mockUnit3.setName("Mock Unit 3");

        Area mockArea12 = new Area();
        mockArea12.setId(AREA_12_ID);
        Area mockArea13 = new Area();
        mockArea13.setId(AREA_13_ID);
        mockAreaList = new ArrayList<>();
        mockAreaList.add(mockArea12);
        mockAreaList.add(mockArea13);
        mockUnit2.setAreas(mockAreaList);

        when(userDetails.getId()).thenReturn(MEMBER_ID);
    }

    @Test
    public void getUnitShouldReturn404IfNotFound()
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        when(unitService.findOne(MEMBER_ID, UNIT_2_ID)).thenThrow(new ItemNotFoundException());
        ResponseEntity<Unit> resp = unitsController.getUnit(userDetails, UNIT_2_ID);
        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
        MultiValueMap<String, String> headers = resp.getHeaders();
        assertEquals(headers.getFirst("error-code"), ExceptionCodes.ITEM_NOT_FOUND.toString());
    }

    @Test
    public void getUnitShouldReturn401IfNotPrivileged()
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        when(unitService.findOne(MEMBER_ID, UNIT_2_ID))
                .thenThrow(new MemberUnprivilegedException());
        ResponseEntity<Unit> resp = unitsController.getUnit(userDetails, UNIT_2_ID);
        assertEquals(resp.getStatusCode(), HttpStatus.UNAUTHORIZED);
        assertEquals(ExceptionCodes.UNPRIVILEGED.toString(),
                resp.getHeaders().getFirst("error-code"));
    }

    @Test
    public void getUnitShouldReturnUnit() throws Exception
    {
        when(unitService.findOne(MEMBER_ID, UNIT_2_ID)).thenReturn(mockUnit2);
        ResponseEntity<Unit> resp = unitsController.getUnit(userDetails, UNIT_2_ID);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        Unit returnedUnit = resp.getBody();
        assertEquals(returnedUnit, mockUnit2);
    }

    @Test
    public void getMembersShouldReturnMembersForUnit()
    {
        Member mockMember2 = new Member();
        Member mockMember3 = new Member();
        List<Member> mockList = new ArrayList<>();
        mockList.add(mockMember2);
        mockList.add(mockMember3);

        when(memberService.findByUnitId(UNIT_2_ID)).thenReturn(mockList);

        ResponseEntity<List<Member>> resp = unitsController.getMembers(UNIT_2_ID);
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
        when(memberService.findByUnitId(UNIT_3_ID)).thenReturn(mockList);

        ResponseEntity<List<Member>> resp = unitsController.getMembers(UNIT_3_ID);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        List<Member> returnedList = resp.getBody();
        assertEquals(returnedList.size(), 0);
    }


}
