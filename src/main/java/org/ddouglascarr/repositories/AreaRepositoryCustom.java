package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Area;

import java.util.List;

public interface AreaRepositoryCustom
{
    List<Area> findByUnitId(Long unitId);
}
