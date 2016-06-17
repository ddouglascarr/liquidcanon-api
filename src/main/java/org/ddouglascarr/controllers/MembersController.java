package org.ddouglascarr.controllers;

import org.ddouglascarr.aop.HandleServiceErrors;
import org.ddouglascarr.command.member.MemberCommandService;
import org.ddouglascarr.command.member.commands.CreateMemberCommand;
import org.ddouglascarr.command.member.requests.CreateMemberRequest;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.models.UserDetailsImpl;
import org.ddouglascarr.query.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MembersController
{
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCommandService memberCommandService;

    @RequestMapping(
            value = "/units/{unitId}/members/{memberId}",
            method = RequestMethod.GET
    )
    @HandleServiceErrors
    public ResponseEntity<Member> getMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable UUID unitId,
            @PathVariable UUID memberId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        Member member = memberService.findOneByUnitIdAndId(
                userDetails.getId(), unitId, memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @HandleServiceErrors
    @RequestMapping(
            value = "/members",
            method = RequestMethod.POST)
    public ResponseEntity<UUID> createMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CreateMemberRequest request)
            throws MemberUnprivilegedException
    {
        UUID id = memberCommandService.create(userDetails.getId(), request.getLogin(),
                request.getPassword(), request.getName(), request.getNotifyEmail());
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
