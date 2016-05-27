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
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class MemberCommandComponentTests
{
    private FixtureConfiguration fixture;

    final UUID ADMIN_MEMBER_ID = UUID.randomUUID();
    final String ADMIN_LOGIN = "test_admin";

    final UUID MEMBER_ID = UUID.randomUUID();
    final String LOGIN = "test_member";
    final String PASSWORD = "test_password";
    final String EMAIL = "test@internet.com";

    @Before
    public void setup()
    {
        fixture = Fixtures.newGivenWhenThenFixture(MemberAggregate.class);
        MemberCommandHandler memberCommandHandler = new MemberCommandHandler(
                fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(memberCommandHandler);
    }

    @Test
    public void createAdminMemberShouldCreateMember() throws Exception
    {
        fixture.given()
                .when(new CreateAdminMemberCommand(ADMIN_MEMBER_ID, ADMIN_LOGIN, PASSWORD))
                .expectEvents(new AdminMemberCreatedEvent(ADMIN_MEMBER_ID, ADMIN_LOGIN, PASSWORD));
    }

    @Test
    public void createMemberShouldThrowIfRequestingMemberIsNotAdmin() throws Exception
    {
        final UUID OTHER_MEMBER_ID = UUID.randomUUID();
        final String OTHER_MEMBER_LOGIN = "other_member";
        final String OTHER_MEMBER_EMAIL = "foo@bar.com";

        fixture.given(new AdminMemberCreatedEvent(ADMIN_MEMBER_ID, ADMIN_LOGIN, PASSWORD),
                        new MemberCreatedEvent(OTHER_MEMBER_ID, OTHER_MEMBER_LOGIN,
                                PASSWORD, OTHER_MEMBER_EMAIL))
                .when(new CreateMemberCommand(OTHER_MEMBER_ID, MEMBER_ID, LOGIN, PASSWORD, EMAIL))
                .expectException(MemberUnprivilegedException.class);
    }

    @Test
    public void createMemberShouldCreateMember() throws Exception
    {
        fixture.given(new AdminMemberCreatedEvent(ADMIN_MEMBER_ID, ADMIN_LOGIN, PASSWORD))
                .when(new CreateMemberCommand(ADMIN_MEMBER_ID, MEMBER_ID, LOGIN, PASSWORD, EMAIL))
                .expectEvents(new MemberCreatedEvent(MEMBER_ID, LOGIN, PASSWORD, EMAIL));
    }

}
