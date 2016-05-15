package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Unit;

public interface UnitRepository
{
    Unit findOneById(Long id);
    Unit findOneByName(String name);
}
