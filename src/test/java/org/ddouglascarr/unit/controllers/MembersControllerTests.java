package org.ddouglascarr.unit.controllers;

import org.ddouglascarr.controllers.MembersController;
import org.ddouglascarr.enums.ExceptionCodes;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MembersControllerTests
{
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MembersController membersController = new MembersController();

    private Member mockMember2;
    private Member mockMember3;

    private List<String> mockRoles = new ArrayList<>();
    private UserDetailsImpl mockUserDetails;
    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockMember2 = new Member();
        mockMember2.setId(new Long(2));

        mockMember3 = new Member();
        mockMember3.setId(new Long(3));

        mockRoles.add("ROLE_USER");
        mockUserDetails = new UserDetailsImpl(mockMember2, mockRoles);
    }

    @Test
    public void getMemberShouldReturn404IfMemberNotFound() throws ItemNotFoundException
    {
        when(memberService.findOne(new Long(6))).thenThrow(new ItemNotFoundException());
        ResponseEntity<Member> resp = membersController.getMember(new Long(6));
        assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
        MultiValueMap<String, String> headers = resp.getHeaders();
        assertEquals(headers.getFirst("error-code"), ExceptionCodes.ITEM_NOT_FOUND.toString());
    }

    @Test
    public void getMemberShouldReturnMember() throws ItemNotFoundException
    {
        when(memberService.findOne(new Long(3))).thenReturn(mockMember3);
        ResponseEntity<Member> resp = membersController.getMember(new Long(3));
        assertEquals(resp.getStatusCode(), HttpStatus.OK);

        Member body = resp.getBody();
        assertEquals(mockMember3, body);
    }


}
