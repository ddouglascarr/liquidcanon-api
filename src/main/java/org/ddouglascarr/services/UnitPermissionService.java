package org.ddouglascarr.services;

import org.ddouglascarr.models.UnitPermission;
import org.springframework.stereotype.Service;

@Service
public interface UnitPermissionService
{
    UnitPermission findOneByUnitId(Long id);
}
