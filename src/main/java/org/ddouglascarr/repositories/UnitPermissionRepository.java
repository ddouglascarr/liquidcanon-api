package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.UnitPermission;

public interface UnitPermissionRepository
{
    UnitPermission findOne(Long id) throws ItemNotFoundException;
    UnitPermission findOneByUnitId(Long unitId) throws ItemNotFoundException;
}
