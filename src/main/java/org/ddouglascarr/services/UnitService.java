package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;

public interface UnitService
{
    Unit findOne(Long id) throws ItemNotFoundException;
}
