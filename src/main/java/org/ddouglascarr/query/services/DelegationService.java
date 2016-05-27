package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Delegation;

import java.util.List;
import java.util.UUID;

public interface DelegationService
{
    Delegation findUnitDelegationForTruster(UUID memberId, UUID unitId, UUID trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException;

    List<Delegation> findIncomingUnitDelegationForTrustee(
            UUID memberId, UUID unitId, UUID trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException;

    Delegation findAreaDelegationForTruster(
            UUID memberId, UUID unitId, UUID areaId, UUID trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException;

    List<Delegation> findIncomingAreaDelegationsForTrustee(
            UUID memberId, UUID unitId, UUID areaId, UUID trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException;

}

