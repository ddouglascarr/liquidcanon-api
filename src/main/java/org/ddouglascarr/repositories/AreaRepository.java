package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long>
{
    Area findOne(Long id);
    List<Area> findByUnitId(Long unitId);
    Area findOneByUnitId(Long unitId, Long id);
}
