package org.ddouglascarr.integration.command;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.command.member.MemberAggregate;
import org.ddouglascarr.command.member.MemberCommandHandler;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;
import org.ddouglascarr.command.member.events.MemberCreatedEvent;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;

@Transactional
public class MemberCommandComponentTests
{
    private FixtureConfiguration fixture;

    @Mock
    private DateUtils dateUtils;

    @InjectMocks
    private MemberCommandHandler memberCommandHandler;

    final UUID ADMIN_MEMBER_ID = UUID.fromString("a4a031fd-57a4-4666-b1fd-afcb75800000");
    final String ADMIN_LOGIN = "test_admin";

    final UUID MEMBER_ID = UUID.fromString("a4a031fd-57a4-4666-b1fd-afcb75811111");
    final String LOGIN = "test_member";
    final String PASSWORD = "test_password";
    final String NAME = "Test Member";
    final String EMAIL = "test@internet.com";

    @Before
    public void setup()
    {
        fixture = Fixtures.newGivenWhenThenFixture(MemberAggregate.class);
        memberCommandHandler = new MemberCommandHandler(
                fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(memberCommandHandler);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createAdminMemberShouldCreateMember() throws Exception
    {
        Date now = new Date();
        when(dateUtils.generateCurrentDate()).thenReturn(now);

        fixture.given()
                .when(new CreateAdminMemberCommand(
                        ADMIN_MEMBER_ID, ADMIN_LOGIN, PASSWORD, NAME, EMAIL))
                .expectEvents(new AdminMemberCreatedEvent(
                        ADMIN_MEMBER_ID, ADMIN_LOGIN, PASSWORD, NAME, EMAIL, now));
    }

    @Test
    public void createMemberShouldCreateMember() throws Exception
    {
        Date now = new Date();

        when(dateUtils.generateCurrentDate()).thenReturn(now);

        fixture.given()
                .when(new CreateMemberCommand(
                        ADMIN_MEMBER_ID, MEMBER_ID, LOGIN, PASSWORD, NAME, EMAIL))
                .expectEvents(new MemberCreatedEvent(
                        ADMIN_MEMBER_ID, MEMBER_ID, LOGIN, PASSWORD, NAME, EMAIL, now ));

    }

}
