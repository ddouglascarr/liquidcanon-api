package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.repositories.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelegationServiceImpl implements DelegationService
{
    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public Delegation findOneById(Long memberId, Long unitId, Long delegationId)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        Delegation delegation = delegationRepository.findOneById(unitId, delegationId);
        if (null == delegation) throw new ItemNotFoundException();
        return delegation;
    }

    @Override
    public List<Delegation> findByTrusterId(Long memberId, Long unitId, Long trusterId)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        List<Delegation> delegations = delegationRepository.findAllForUnitByTrusterId(unitId, trusterId);
        return delegations;
    }

    @Override
    public List<Delegation> findByTrusteeId(Long memberId, Long unitId, Long trusteeId)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        return delegationRepository.findAllForUnitByTrusteeId(unitId, trusteeId);
    }
}
