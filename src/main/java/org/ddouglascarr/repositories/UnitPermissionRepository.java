package org.ddouglascarr.repositories;

import org.ddouglascarr.models.UnitPermission;

public interface UnitPermissionRepository
{
    UnitPermission findOne(Long id);
    UnitPermission findOneByUnitId(Long unitId);
}
