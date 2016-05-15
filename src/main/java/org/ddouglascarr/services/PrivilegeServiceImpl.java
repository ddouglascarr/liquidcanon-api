package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Privilege;
import org.ddouglascarr.models.UnitPermission;
import org.ddouglascarr.repositories.PrivilegeRepository;
import org.ddouglascarr.repositories.UnitPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeService
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UnitPermissionRepository unitPermissionRepository;

    @Override
    public void assertUnitReadPrivilege(Long memberId, Long unitId)
            throws MemberUnprivilegedException
    {
        UnitPermission unitPermission;
        Boolean hasUnitReadPermission = false;
        try {
            unitPermission = unitPermissionRepository.findOneByUnitId(unitId);
            if (unitPermission.getPublicRead()) return;
        } catch (ItemNotFoundException e) {
            hasUnitReadPermission = false;
        }

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
