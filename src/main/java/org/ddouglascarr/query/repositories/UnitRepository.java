package org.ddouglascarr.query.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Unit;

import java.util.UUID;

public interface UnitRepository
{
    Unit findOneById(UUID id) throws ItemNotFoundException;
}
