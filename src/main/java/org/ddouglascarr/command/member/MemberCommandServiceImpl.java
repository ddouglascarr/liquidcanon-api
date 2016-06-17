package org.ddouglascarr.command.member;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.Repository;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.services.MemberService;
import org.ddouglascarr.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MemberCommandServiceImpl implements MemberCommandService
{
    @Autowired
    private MemberService memberService;

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private IdUtils idUtils;

    @Override
    public UUID create(UUID requestingMemberId, String login, String password, String name,
                       String notifyEmail)
            throws MemberUnprivilegedException
    {

        try {
            Member requestingMember = memberService.findOne(requestingMemberId);
            if (!requestingMember.getAdmin()) throw new MemberUnprivilegedException();
        } catch (ItemNotFoundException e) {
            throw new MemberUnprivilegedException();
        }
        UUID id = idUtils.generateUniqueId();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        commandGateway.send(new CreateMemberCommand(requestingMemberId, id,
                login, encodedPassword, name, notifyEmail));
        return id;
    }
}
