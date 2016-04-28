package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DelegationRepository extends JpaRepository<Delegation, Long>
{
    List<Delegation> findByUnitIdAndTrusterId(Long unitId, Long trusterId);
    List<Delegation> findByUnitIdAndTrusteeId(Long unitId, Long trusteeId);
    Delegation findOneById(Long unitId, Long id);
}
