package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;

import java.util.List;
import java.util.UUID;

public interface AreaRepository
{
    Area findOne(UUID id) throws ItemNotFoundException;
    Area findOneByUnitIdAndId(UUID unitId, UUID id) throws ItemNotFoundException;
    List<Area> findByUnitId(UUID unitId);

}
