package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;

import java.util.List;

public interface DelegationService
{
    Delegation findUnitDelegationForTruster(Long memberId, Long unitId, Long trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException;

    List<Delegation> findIncomingUnitDelegationForTrustee(
            Long memberId, Long unitId, Long trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException;

    Delegation findAreaDelegationForTruster(
            Long memberId, Long unitId, Long areaId, Long trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException;

    List<Delegation> findIncomingAreaDelegationsForTrustee(
            Long memberId, Long unitId, Long areaId, Long trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException;

}

