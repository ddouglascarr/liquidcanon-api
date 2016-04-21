package org.ddouglascarr.unit;

import org.ddouglascarr.models.Member;
import org.ddouglascarr.services.MemberServiceImpl;
import org.ddouglascarr.repositories.MemberRepository;
import org.ddouglascarr.services.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @Test public void findOneShouldReturnMember()
    {
        when(memberRepository.findOneById(new Long(2))).thenReturn(mockMember2);
        Member returnedMember = memberService.findOne(new Long(2));
        assertEquals(returnedMember, mockMember2);
        assertNotEquals(returnedMember, mockMember3);
    }

    @Test
    public void findOneShouldReturnNullIfMemberNotFound()
    {
        when(memberRepository.findOneById(new Long(6))).thenReturn(null);
        Member returnedMember = memberService.findOne(new Long(6));
        assertNull(returnedMember);
    }


    @Test
    public void findOneByLoginShouldReturnMember()
    {
        when(memberRepository.findOneByLogin("mock_member_2")).thenReturn(mockMember2);
        Member returnedMember = memberService.findOneByLogin("mock_member_2");
        assertEquals(returnedMember, mockMember2);
    }

    @Test
    public void findOneByLoginShouldReturnNullWhenMemberNotFound()
    {
        when(memberRepository.findOneByLogin("mock_member_404")).thenReturn(null);
        Member returnedMember = memberService.findOneByLogin("mock_member_404");
        assertNull(returnedMember);
    }

}
