package org.ddouglascarr.unit.controllers;

import org.ddouglascarr.controllers.MembersController;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.models.UserDetailsImpl;
import org.ddouglascarr.query.services.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

public class MembersControllerTests
{
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MembersController membersController = new MembersController();

    @Mock
    private Member mockMember2;
    @Mock
    private Member mockMember3;
    @Mock
    private UserDetailsImpl mockUserDetails;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        when(mockMember2.getId()).thenReturn(HUGLE_MEMBER_ID);
        when(mockMember3.getId()).thenReturn(ALMEIDA_MEMBER_ID);

        when(mockUserDetails.getId()).thenReturn(HUGLE_MEMBER_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void getMemberShouldReturn404IfMemberNotFound() throws Exception
    {
        when(memberService.findOneByUnitIdAndId(HUGLE_MEMBER_ID, MOON_UNIT_ID, ALMEIDA_MEMBER_ID))
                .thenThrow(new ItemNotFoundException());
        membersController.getMember(mockUserDetails, MOON_UNIT_ID, ALMEIDA_MEMBER_ID);
    }

    @Test
    public void getMemberShouldReturnMember() throws Exception
    {
        when(memberService.findOneByUnitIdAndId(HUGLE_MEMBER_ID, EARTH_UNIT_ID, ALMEIDA_MEMBER_ID))
                .thenReturn(mockMember3);
        ResponseEntity<Member> resp = membersController
                .getMember(mockUserDetails, EARTH_UNIT_ID, ALMEIDA_MEMBER_ID);
        assertEquals(resp.getStatusCode(), HttpStatus.OK);
        Member body = resp.getBody();
        assertEquals(mockMember3, body);
    }


}
