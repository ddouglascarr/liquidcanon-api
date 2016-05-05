package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DelegationRepository
{
    Delegation findOneUnitDelegationByTrusterId(Long unitId, Long trusterId);
//    Delegation findOneAreaDelegationByTrusterId(Long unitId, Long areaId, Long trusterId);
//    Delegation findOneIssueDelegationByTrusterId(Long unitId, Long issueId, Long trusterId);
    Delegation findOneById(Long unitId, Long id);

//    List<Delegation> findAreaDelegationsForUnitByTrusterId(Long unitId, Long trusterId);
//    List<Delegation> findIssueDelegationsForAreaByTrusterId(Long unitId, Long trusterId);
    List<Delegation> findAllForUnitByTrusterId(Long unitId, Long trusterId);


    List<Delegation> findAllForUnitByTrusteeId(Long unitId, Long trusteeId);
}
