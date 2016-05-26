package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;

import java.util.List;
import java.util.UUID;

public interface DelegationRepository
{
    Delegation findUnitDelegationByTrusterId(UUID unitId, UUID trusterId);
    Delegation findAreaDelegationByTrusterId(UUID unitId, UUID areaId, UUID trusterId);
    List<Delegation> findAreaDelegationsByUnitIdAndTrusterId(UUID unitId, UUID trusterId);
//    Delegation findIssueDelegationByTrusterId(UUID unitId, UUID issueId, UUID trusterId);
    Delegation findOneById(UUID unitId, UUID id);

//    List<Delegation> findAreaDelegationsForUnitByTrusterId(UUID unitId, UUID trusterId);
//    List<Delegation> findIssueDelegationsForAreaByTrusterId(UUID unitId, UUID trusterId);
    List<Delegation> findByTrusterId(UUID unitId, UUID trusterId);


    List<Delegation> findUnitDelegationsByTrusteeId(UUID unitId, UUID trusteeId);
    List<Delegation> findAreaDelegationsByTrusteeId(UUID unitId, UUID areaId, UUID trusteeId);

}
