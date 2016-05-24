package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Privilege;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.models.UnitPermission;
import org.ddouglascarr.repositories.PrivilegeRepository;
import org.ddouglascarr.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeService
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public void assertUnitReadPrivilege(Long memberId, Long unitId)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        Unit unit;
        Boolean hasUnitReadPermission = false;
        unit = unitRepository.findOneById(unitId);
        if (unit.getPublicRead()) return;

        try {
            Privilege privilege = privilegeRepository
                    .findOneByMemberIdAndUnitId(memberId, unitId);
            return;
        } catch (Exception e) {
            throw new MemberUnprivilegedException();
        }
    }

    @Override
    public void assertUnitVotingPrivilege(Long memberId, Long unitId)
            throws MemberUnprivilegedException
    {
        try {
            Privilege privilege = privilegeRepository.findOneByMemberIdAndUnitId(memberId, unitId);
            if (privilege.getVotingRight()) return;
            throw new MemberUnprivilegedException();
        } catch (ItemNotFoundException e) {
            throw new MemberUnprivilegedException();
        }
    }
}
