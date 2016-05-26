package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.repositories.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DelegationServiceImpl implements DelegationService
{
    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public Delegation findUnitDelegationForTruster(UUID memberId, UUID unitId, UUID trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        Delegation delegation = delegationRepository
                .findUnitDelegationByTrusterId(unitId, trusterId);
        if (null == delegation) throw new ItemNotFoundException();
        return delegation;
    }

    @Override
    public List<Delegation> findIncomingUnitDelegationForTrustee(
            UUID memberId, UUID unitId, UUID trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        List<Delegation> delegations = delegationRepository
                .findUnitDelegationsByTrusteeId(unitId, trusteeId);
        return delegations;
    }

    @Override
    public Delegation findAreaDelegationForTruster(
            UUID memberId, UUID unitId, UUID areaId, UUID trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        Delegation delegation = delegationRepository
                .findAreaDelegationByTrusterId(unitId, areaId, trusterId);
        if (null == delegation) throw new ItemNotFoundException();
        return delegation;
    }

    @Override
    public List<Delegation> findIncomingAreaDelegationsForTrustee(
            UUID memberId, UUID unitId, UUID areaId, UUID trusteeId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        List<Delegation> delegations = delegationRepository
                .findAreaDelegationsByTrusteeId(unitId, areaId, trusteeId);
        return delegations;
    }
}
