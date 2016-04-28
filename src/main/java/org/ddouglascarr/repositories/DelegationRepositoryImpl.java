package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DelegationRepositoryImpl implements DelegationRepository
{
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Delegation> findByUnitIdAndTrusterId(Long unitId, Long trusterId)
    {
        /*final String sql = String.join(" ",
                "SELECT delegation.* FROM",
                "(SELECT * FROM delegation WHERE truster_id = :trusterId) AS delegation",

        );*/
        return null;
    }

    @Override
    public List<Delegation> findByUnitIdAndTrusteeId(Long unitId, Long trusteeId)
    {
        return null;
    }

    @Override
    public Delegation findOneById(Long unitId, Long id)
    {
        return null;
    }
}
