package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by daniel on 17/04/16.
 */
@Service
public class MemberServiceImpl implements MemberService
{
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PrivilegeService privilegeService;

    @Override
    public Member findOne(UUID id) throws ItemNotFoundException
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

    @Override
    public Member findOneByUnitIdAndId(UUID userDetailsId, UUID unitId, UUID id) throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(userDetailsId, unitId);
        Member member = memberRepository.findOneByUnitIdAndId(unitId, id);
        return member;
    }

    @Override
    public List<Member> findByUnitId(UUID unitId)
    {
        List<Member> members = memberRepository.findByUnitId(unitId);
        return members;
    }

    @Override
    public Boolean isAtLeastOneAdminMember()
    {
        return memberRepository.isAtLeastOneAdminMember();
    }
}
