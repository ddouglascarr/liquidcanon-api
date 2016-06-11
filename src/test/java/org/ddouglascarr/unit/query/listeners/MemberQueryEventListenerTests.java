package org.ddouglascarr.unit.query.listeners;

import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;
import org.ddouglascarr.query.listeners.MemberQueryEventListener;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.services.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

public class MemberQueryEventListenerTests
{
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberQueryEventListener listener = new MemberQueryEventListener();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void handleAdminMemberCreatedEvent() throws Exception
    {
        Date now = new Date();
        AdminMemberCreatedEvent event = new AdminMemberCreatedEvent(
                ADMIN_MEMBER_ID, "admin", "password1234", "Test Name", "test@email.com", now);
        ArgumentCaptor<Member> argument = ArgumentCaptor.forClass(Member.class);

        listener.handle(event);
        verify(memberService).create(argument.capture());
        Member member = argument.getValue();
        assertEquals(ADMIN_MEMBER_ID, member.getId());
        assertEquals("admin", member.getLogin());
        assertEquals("password1234", member.getPassword());
        assertEquals(true, member.getAdmin());
        assertEquals(true, member.getActive());
        assertEquals(now, member.getActivated());
        assertEquals("Test Name", member.getName());
        assertEquals("test@email.com", member.getNotifyEmail());
    }
}
