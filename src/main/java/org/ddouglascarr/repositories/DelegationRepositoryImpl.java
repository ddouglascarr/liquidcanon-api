package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DelegationRepositoryImpl implements DelegationRepository
{
    @Override
    public Delegation findOneUnitDelegationByTrusterId(Long unitId, Long trusterId)
    {
        return null;
    }

    @Override
    public Delegation findOneById(Long unitId, Long id)
    {
        return null;
    }

    @Override
    public List<Delegation> findAllForUnitByTrusterId(Long unitId, Long trusterId)
    {
        return null;
    }

    @Override
    public List<Delegation> findAllForUnitByTrusteeId(Long unitId, Long trusteeId)
    {
        return null;
    }
}
