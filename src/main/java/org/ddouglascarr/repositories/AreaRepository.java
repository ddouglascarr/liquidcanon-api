package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;

import java.util.List;

public interface AreaRepository
{
    Area findOne(Long id) throws ItemNotFoundException;
    Area findOneByUnitIdAndId(Long unitId, Long id) throws ItemNotFoundException;
    List<Area> findByUnitId(Long unitId);

}
