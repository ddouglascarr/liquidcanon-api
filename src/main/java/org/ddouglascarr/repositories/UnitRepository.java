package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;

public interface UnitRepository
{
    Unit findOneById(Long id) throws ItemNotFoundException;
}
