package org.ddouglascarr.unit.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.services.MemberServiceImpl;
import org.ddouglascarr.query.repositories.MemberRepository;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.query.services.PrivilegeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;


public class MemberServiceImplTests
{
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PrivilegeService privilegeService;

    @InjectMocks
    private MemberService memberService = new MemberServiceImpl();

    @Mock private Member mockMember2;
    @Mock private Member mockMember3;
    @Mock private Member mockMember4;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        when(mockMember2.getId()).thenReturn(HUGLE_MEMBER_ID);
        when(mockMember3.getId()).thenReturn(ALMEIDA_MEMBER_ID);
        when(mockMember4.getId()).thenReturn(POITRAS_MEMBER_ID);
    }

    @Test
    public void findOneShouldReturnMember() throws ItemNotFoundException
    {
        when(memberRepository.findOneById(HUGLE_MEMBER_ID)).thenReturn(mockMember2);
        Member returnedMember = memberService.findOne(HUGLE_MEMBER_ID);
        assertEquals(returnedMember, mockMember2);
        assertNotEquals(returnedMember, mockMember3);
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfMemberNotFound() throws ItemNotFoundException
    {
        doThrow(new ItemNotFoundException()).when(memberRepository)
                .findOneById(NON_EXISTANT_MEMBER_ID);
        memberService.findOne(NON_EXISTANT_MEMBER_ID);
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
        doThrow(new ItemNotFoundException()).when(memberRepository)
                .findOneByLogin("mock_member_404");
        memberService.findOneByLogin("mock_member_404");
    }

    @Test
    public void findByUnitIdShouldReturnListOfMembers()
    {
        List<Member> mockList = new ArrayList<>();
        mockList.add(mockMember2);
        mockList.add(mockMember3);
        when(memberRepository.findByUnitId(EARTH_UNIT_ID)).thenReturn(mockList);
        List<Member> returnedList = memberService.findByUnitId(EARTH_UNIT_ID);
        assertEquals(returnedList.size(), 2);
        assertEquals(returnedList.get(0), mockMember2);
        assertEquals(returnedList.get(1), mockMember3);
    }

    @Test
    public void isAtLeastOneAdminMemberShouldReturnFalseIfNoAdmins()
    {
        when(memberRepository.isAtLeastOneAdminMember()).thenReturn(false);
        assertEquals(false, memberService.isAtLeastOneAdminMember());
    }

    @Test
    public void isAtLeastOneAdminMemberShouldReturnTrueIfThereAreAdmins()
    {
        when(memberRepository.isAtLeastOneAdminMember()).thenReturn(true);
        assertEquals(true, memberService.isAtLeastOneAdminMember());
    }

    @Test
    public void createShouldCallCreateOnRepository()
    {
        memberService.create(mockMember2);
        verify(memberRepository, times(1)).create(mockMember2);
    }

}
