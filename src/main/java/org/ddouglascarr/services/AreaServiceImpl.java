package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
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

    @Override
    public Area findOne(Long id) throws ItemNotFoundException
    {
        Area area = areaRepository.findOne(id);
        if (null == area) throw new ItemNotFoundException();
        return area;
    }

    @Override
    public List<Area> findByUnitId(Long unitId)
    {
        List<Area> areas = areaRepository.findByUnitId(unitId);
        return areas;
    }

    @Override
    public Area findOneByUnitId(Long unitId, Long id) throws ItemNotFoundException
    {
        Area area = areaRepository.findOneByUnitId(unitId, id);
        if (null == area) throw new ItemNotFoundException();
        return area;
    }
}
