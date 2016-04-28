package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;

import java.util.List;

public interface DelegationService
{
    Delegation findOneById(Long memberId, Long unitId, Long id)
            throws MemberUnprivilegedException, ItemNotFoundException;

    List<Delegation> findByTrusterId(Long memberId, Long unitId, Long trusterId)
            throws MemberUnprivilegedException, ItemNotFoundException;

    List<Delegation> findByTrusteeId(Long memberId, Long unitId, Long trusteeId)
            throws MemberUnprivilegedException, ItemNotFoundException;

}
