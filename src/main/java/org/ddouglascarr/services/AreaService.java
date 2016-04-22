package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;

import java.util.List;

public interface AreaService
{
    Area findOne(Long id) throws ItemNotFoundException;
    Area findOneByUnitId(Long unitId, Long id) throws ItemNotFoundException;
    List<Area> findByUnitId(Long unitId);
}
