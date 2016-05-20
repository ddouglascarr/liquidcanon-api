package org.ddouglascarr.unit.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.services.MemberServiceImpl;
import org.ddouglascarr.repositories.MemberRepository;
import org.ddouglascarr.services.MemberService;
import org.ddouglascarr.services.PrivilegeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.ddouglascarr.utils.IntegrationTestConsts.*;


public class MemberServiceImplTests
{
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PrivilegeService privilegeService;

    @InjectMocks
    private MemberService memberService = new MemberServiceImpl();

    private Member mockMember2;
    private Member mockMember3;

    @Mock
    private Member mockMember4;

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

        when(mockMember4.getId()).thenReturn(HUGLE_MEMBER_ID);
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

    @Test(expected = MemberUnprivilegedException.class)
    public void findOneByUnitIdAndIdShouldThrowIfNotUnitReadPrivileged()
            throws Exception
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(POITRAS_MEMBER_ID, MARS_UNIT_ID);
        memberService.findOneByUnitIdAndId(POITRAS_MEMBER_ID, MARS_UNIT_ID, SAHA_MEMBER_ID);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByUnitIdAndIDSHouldThrowIfMemberNotFound() throws Exception
    {
        doThrow(new ItemNotFoundException()).when(memberRepository)
                .findOneByUnitIdAndId(EARTH_UNIT_ID, KHORANA_MEMBER_ID);
        memberService.findOneByUnitIdAndId(POITRAS_MEMBER_ID, EARTH_UNIT_ID, KHORANA_MEMBER_ID);
    }

    @Test
    public void findOneByUnitIdAndIdShouldReturnMember() throws Exception
    {
        when(memberRepository.findOneByUnitIdAndId(EARTH_UNIT_ID, HUGLE_MEMBER_ID))
                .thenReturn(mockMember4);
        Member returnedMember = memberService
                .findOneByUnitIdAndId(POITRAS_MEMBER_ID, EARTH_UNIT_ID, HUGLE_MEMBER_ID);
        assertEquals(mockMember4, returnedMember);
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
