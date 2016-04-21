package org.ddouglascarr.controllers;

import org.ddouglascarr.exceptions.ItemNotFoundException;
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
public class MembersController
{
    @Autowired
    private MemberService memberService;

    @RequestMapping(
            value = "/members/{memberId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Member> getMember(@PathVariable Long memberId)
    {
        try {
            Member member = memberService.findOne(memberId);
            return new ResponseEntity<Member>(member, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<Member>(e.getResponseHeaders(), HttpStatus.NOT_FOUND);
        }
    }

}
