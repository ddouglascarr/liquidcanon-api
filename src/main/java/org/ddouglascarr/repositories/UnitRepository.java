package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;

import java.util.UUID;

public interface UnitRepository
{
    Unit findOneById(UUID id) throws ItemNotFoundException;
}
