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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    private UUID ADMIN_MEMBER_ID = UUID.randomUUID();
    private UUID MEMBER_ID = UUID.randomUUID();
    private UUID OTHER_MEMBER_ID = UUID.randomUUID();

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
        when(mockCommand.getRequestingMemberId()).thenReturn(OTHER_MEMBER_ID);
        memberCommandService.create(mockCommand);
    }

    @Test
    public void createShouldCallCommandBusAndReturnId() throws Exception
    {
        when(mockCommand.getRequestingMemberId()).thenReturn(ADMIN_MEMBER_ID);
        UUID returnedId = memberCommandService.create(mockCommand);
        verify(commandGateway, times(1)).send(mockCommand);
        assertEquals(MEMBER_ID, returnedId);
    }
}
