package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long>, AreaRepositoryCustom
{
    Area findOne(Long id);
    Area findOneByUnitIdAndId(Long unitId, Long id);
}
