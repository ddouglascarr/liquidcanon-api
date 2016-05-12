package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.repositories.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DelegationServiceImpl implements DelegationService
{
    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public Delegation findUnitDelegationForTruster(Long memberId, Long unitId, Long trusterId)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        Delegation delegation = delegationRepository
                .findUnitDelegationByTrusterId(unitId, trusterId);
        if (null == delegation) throw new ItemNotFoundException();
        return delegation;
    }

    @Override
    public List<Delegation> findIncomingUnitDelegationForTrustee(Long memberId, Long unitId, Long trusteeId) throws ItemNotFoundException, MemberUnprivilegedException
    {
        return null;
    }

    @Override
    public Delegation findAreaDelegationForTruster(Long memberId, Long unitId, Long areaId, Long trusteeId) throws ItemNotFoundException, MemberUnprivilegedException
    {
        return null;
    }

    @Override
    public List<Delegation> findIncomingAreaDelegationsForTrustee(Long memberId, Long unitId, Long areaId, Long trusteeId) throws ItemNotFoundException, MemberUnprivilegedException
    {
        return null;
    }
}
