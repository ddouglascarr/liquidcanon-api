package org.ddouglascarr.unit.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.services.MemberServiceImpl;
import org.ddouglascarr.repositories.MemberRepository;
import org.ddouglascarr.services.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by daniel on 21/04/16.
 */
public class MemberServiceImplTests
{
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService = new MemberServiceImpl();

    private Member mockMember2;
    private Member mockMember3;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockMember2 = new Member();
        mockMember2.setId(new Long(2));
        mockMember2.setLogin("mock_member_2");

        mockMember3 = new Member();
        mockMember3.setId(new Long(3));
        mockMember3.setLogin("mock_member_3");
    }

    @Test
    public void findOneShouldReturnMember() throws ItemNotFoundException
    {
        when(memberRepository.findOneById(new Long(2))).thenReturn(mockMember2);
        Member returnedMember = memberService.findOne(new Long(2));
        assertEquals(returnedMember, mockMember2);
        assertNotEquals(returnedMember, mockMember3);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfMemberNotFound() throws ItemNotFoundException
    {
        when(memberRepository.findOneById(new Long(6))).thenReturn(null);
        Member returnedMember = memberService.findOne(new Long(6));
        fail("should have thrown");
    }


    @Test
    public void findOneByLoginShouldReturnMember() throws ItemNotFoundException
    {
        when(memberRepository.findOneByLogin("mock_member_2")).thenReturn(mockMember2);
        Member returnedMember = memberService.findOneByLogin("mock_member_2");
        assertEquals(returnedMember, mockMember2);
    }

    @Test(expected = ItemNotFoundException.class )
    public void findOneByLoginShouldThrowIfMemberNotFound() throws ItemNotFoundException
    {
        when(memberRepository.findOneByLogin("mock_member_404")).thenReturn(null);
        Member returnedMember = memberService.findOneByLogin("mock_member_404");
    }

    @Test
    public void findByUnitIdShouldReturnListOfMembers()
    {
        List<Member> mockList = new ArrayList<>();
        mockList.add(mockMember2);
        mockList.add(mockMember3);
        when(memberRepository.findByUnitId(new Long(2))).thenReturn(mockList);
        List<Member> returnedList = memberService.findByUnitId(new Long(2));
        assertEquals(returnedList.size(), 2);
        assertEquals(returnedList.get(0), mockMember2);
        assertEquals(returnedList.get(1), mockMember3);
    }

}
