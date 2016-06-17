package org.ddouglascarr.unit.command.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.MemberAggregate;
import org.ddouglascarr.command.member.MemberCommandService;
import org.ddouglascarr.command.member.MemberCommandServiceImpl;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.command.member.requests.CreateMemberRequest;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.utils.IdUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MemberCommandServiceTests
{
    @Mock
    private Repository repository;

    @Mock
    private CommandGateway commandGateway;

    @Mock
    private IdUtils idUtils;

    @InjectMocks
    private MemberCommandService memberCommandService = new MemberCommandServiceImpl();

    @Mock private CreateMemberRequest mockRequest;
    @Mock private CreateMemberCommand mockCommand;
    @Mock private MemberAggregate mockMemberAggregate;
    @Mock private MemberAggregate mockAdminMemberAggregate;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UUID ADMIN_MEMBER_ID = UUID.randomUUID();
    private UUID MEMBER_ID = UUID.randomUUID();
    private UUID OTHER_MEMBER_ID = UUID.randomUUID();

    private String LOGIN = "mocklogin";
    private String PASSWORD = "mockPasssword";
    private String NAME = "Mock Name";
    private String EMAIL = "mock@email.com";

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);


        when(idUtils.generateUniqueId()).thenReturn(MEMBER_ID);
        when(repository.load(ADMIN_MEMBER_ID)).thenReturn(mockAdminMemberAggregate);
        when(repository.load(OTHER_MEMBER_ID)).thenReturn(mockMemberAggregate);
        when(mockMemberAggregate.getId()).thenReturn(MEMBER_ID);
        when(mockMemberAggregate.getAdmin()).thenReturn(false);
        when(mockAdminMemberAggregate.getId()).thenReturn(ADMIN_MEMBER_ID);
        when(mockAdminMemberAggregate.getAdmin()).thenReturn(true);

    }

    @Test(expected = MemberUnprivilegedException.class)
    public void createShouldThrowIfRequestingMemberIsNotAdmin() throws Exception
    {
        memberCommandService.create(OTHER_MEMBER_ID, LOGIN, PASSWORD, NAME, EMAIL);
    }

    @Test
    public void createShouldCallCommandBusAndReturnId() throws Exception
    {
        ArgumentCaptor<CreateMemberCommand> argument = ArgumentCaptor.forClass(
                CreateMemberCommand.class);

        UUID returnedId = memberCommandService.create(
                ADMIN_MEMBER_ID, LOGIN, PASSWORD, NAME, EMAIL);

        assertEquals(MEMBER_ID, returnedId);
        verify(commandGateway).send(argument.capture());
        CreateMemberCommand command = argument.getValue();
        assertEquals(MEMBER_ID, command.getId());
        assertEquals(ADMIN_MEMBER_ID, command.getRequestingMemberId());
        assertEquals(LOGIN, command.getLogin());
        assertTrue(passwordEncoder.matches(PASSWORD, command.getPassword()));
        assertEquals(NAME, command.getName());
        assertEquals(EMAIL, command.getNotifyEmail());
    }
}
