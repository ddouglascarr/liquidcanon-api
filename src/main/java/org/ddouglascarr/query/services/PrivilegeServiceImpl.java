package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Privilege;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.repositories.PrivilegeRepository;
import org.ddouglascarr.query.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrivilegeServiceImpl implements PrivilegeService
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public void assertUnitReadPrivilege(UUID memberId, UUID unitId)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        Unit unit;
        Boolean hasUnitReadPermission = false;
        unit = unitRepository.findOne(unitId);
        if (null == unit) throw new ItemNotFoundException();
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
    public void assertUnitVotingPrivilege(UUID memberId, UUID unitId)
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
