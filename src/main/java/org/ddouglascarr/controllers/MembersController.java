package org.ddouglascarr.controllers;

import org.ddouglascarr.aop.HandleServiceErrors;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/units/{unitId}")
public class MembersController
{
    @Autowired
    private MemberService memberService;

    @RequestMapping(
            value = "/members/{memberId}",
            method = RequestMethod.GET
    )
    @HandleServiceErrors
    public ResponseEntity<Member> getMember(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long unitId,
            @PathVariable Long memberId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        Member member = memberService.findOneByUnitIdAndId(
                userDetails.getId(), unitId, memberId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

}
