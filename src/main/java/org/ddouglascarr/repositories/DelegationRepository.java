package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DelegationRepository
{
    Delegation findUnitDelegationByTrusterId(Long unitId, Long trusterId);
    Delegation findAreaDelegationByTrusterId(Long unitId, Long areaId, Long trusterId);
    List<Delegation> findAreaDelegationsByUnitIdAndTrusterId(Long unitId, Long trusterId);
//    Delegation findIssueDelegationByTrusterId(Long unitId, Long issueId, Long trusterId);
    Delegation findOneById(Long unitId, Long id);

//    List<Delegation> findAreaDelegationsForUnitByTrusterId(Long unitId, Long trusterId);
//    List<Delegation> findIssueDelegationsForAreaByTrusterId(Long unitId, Long trusterId);
    List<Delegation> findByTrusterId(Long unitId, Long trusterId);


    List<Delegation> findUnitDelegationsByTrusteeId(Long unitId, Long trusteeId);
    List<Delegation> findAreaDelegationsByTrusteeId(Long unitId, Long areaId, Long trusteeId);

}
