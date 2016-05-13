package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository
{
    @Override
    public Member findOneById(Long id) throws ItemNotFoundException
    {
        return null;
    }

    @Override
    public Member findOneByLogin(String login) throws ItemNotFoundException
    {
        return null;
    }

    @Override
    public List<Member> findByUnitId(Long unitId)
    {
        return null;
    }
}
