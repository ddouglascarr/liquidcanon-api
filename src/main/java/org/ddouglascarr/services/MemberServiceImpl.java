package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
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
    public Member findOne(Long id) throws ItemNotFoundException
    {
        Member member = memberRepository.findOneById(id);
        if (null == member) throw new ItemNotFoundException();
        return member;
    }

    @Override
    public Member findOneByLogin(String login) throws ItemNotFoundException
    {
        Member member = memberRepository.findOneByLogin(login);
        if (null == member) throw new ItemNotFoundException();
        return member;
    }
}
