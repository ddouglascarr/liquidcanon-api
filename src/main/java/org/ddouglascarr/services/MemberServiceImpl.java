package org.ddouglascarr.services;

import org.ddouglascarr.models.Member;
import org.ddouglascarr.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 17/04/16.
 */
@Service
public class MemberServiceImpl implements MemberService
{
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Member findOne(Long id)
    {
        Member member = memberRepository.findOneById(id);
        return member;
    }

    @Override
    public Member findOneByLogin(String login)
    {
        Member member = memberRepository.findOneByLogin(login);
        return member;
    }
}
