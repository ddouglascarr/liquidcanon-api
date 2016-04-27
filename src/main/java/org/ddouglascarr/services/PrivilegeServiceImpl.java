package org.ddouglascarr.services;

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
        UnitPermission unitPermission = unitPermissionRepository.findOneByUnitId(unitId);
        if (null != unitPermission && unitPermission.getPublicRead()) return;
        Privilege privilege = privilegeRepository.findOneByMemberIdAndUnitId(memberId, unitId);
        if (null != privilege) return;
        throw new MemberUnprivilegedException();
    }

    @Override
    public void assertUnitVotingPrivilege(Long memberId, Long unitId)
            throws MemberUnprivilegedException
    {
        Privilege privilege = privilegeRepository.findOneByMemberIdAndUnitId(memberId, unitId);
        if (null == privilege) throw new MemberUnprivilegedException();
        if (privilege.getVotingRight()) return;
        throw new MemberUnprivilegedException();
    }
}
