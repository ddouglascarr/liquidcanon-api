package org.ddouglascarr.repositories;

import org.ddouglascarr.models.UnitPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitPermissionRepository extends JpaRepository<UnitPermission, Long>
{
    UnitPermission findOne(Long id);
    UnitPermission findOneByUnitId(Long unitId);
}
