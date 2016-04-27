package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;
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
    public List<Area> findByUnitId(Long memberId, Long unitId)
                throws MemberUnprivilegedException
    {
        privilegeService.assertUnitReadPrivilege(memberId, unitId);
        List<Area> areas = areaRepository.findByUnitId(unitId);
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
