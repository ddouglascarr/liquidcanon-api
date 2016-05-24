package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UnitServiceImpl implements UnitService
{
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public Unit findOne(UUID memberId, UUID id)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, id);
        Unit unit = unitRepository.findOneById(id);
        if (null == unit) throw new ItemNotFoundException();
        return unit;
    }
}
