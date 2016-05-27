package org.ddouglascarr.integration.command;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.ddouglascarr.command.member.MemberAggregate;
import org.ddouglascarr.command.member.MemberCommandHandler;
import org.ddouglascarr.command.member.commands.CreateAdminMemberCommand;
import org.ddouglascarr.command.member.events.AdminMemberCreatedEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class MemberCommandComponentTests
{
    private FixtureConfiguration fixture;

    final UUID MEMBER_ID = UUID.randomUUID();
    final String LOGIN = "test_admin";
    final String PASSWORD = "test_password";

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
                .when(new CreateAdminMemberCommand(MEMBER_ID, LOGIN, PASSWORD))
                .expectEvents(new AdminMemberCreatedEvent(MEMBER_ID, LOGIN, PASSWORD));
    }

}
