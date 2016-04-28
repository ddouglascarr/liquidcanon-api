package org.ddouglascarr.services;

import org.ddouglascarr.aop.Foo;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService
{
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private UnitService unitService;

    @Autowired
    PrivilegeService privilegeService;

    @Override
    public Area findOne(Long memberId, Long id)
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        Area area = areaRepository.findOne(id);
        if (null == area) throw new ItemNotFoundException();
        privilegeService.assertUnitReadPrivilege(memberId, area.getUnitId());
        return area;
    }

    @Override
    @Foo
    public List<Area> findByUnitId(Long memberId, Long unitId)
                throws MemberUnprivilegedException, ItemNotFoundException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        List<Area> areas = areaRepository.findByUnitId(unitId);
        if (1 > areas.size()) {
            Unit unit = unitService.findOne(memberId, unitId);
            if (null == unit) throw new ItemNotFoundException();
        }
        return areas;
    }

    @Override
    public Area findOneByUnitId(Long memberId, Long unitId, Long id)
            throws MemberUnprivilegedException, ItemNotFoundException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        Area area = areaRepository.findOneByUnitIdAndId(unitId, id);
        if (null == area) throw new ItemNotFoundException();
        return area;
    }
}