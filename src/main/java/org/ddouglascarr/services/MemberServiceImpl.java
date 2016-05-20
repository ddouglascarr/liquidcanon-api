package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Member findOneByUnitIdAndId(Long userDetailsId, Long unitId, Long id) throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(userDetailsId, unitId);
        Member member = memberRepository.findOneByUnitIdAndId(unitId, id);
        return member;
    }

    @Override
    public List<Member> findByUnitId(Long unitId)
    {
        List<Member> members = memberRepository.findByUnitId(unitId);
        return members;
    }


}
