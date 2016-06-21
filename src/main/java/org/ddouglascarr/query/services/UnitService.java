package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Unit;

import java.util.UUID;

public interface UnitService
{
    Unit findOne(UUID memberId, UUID id)
            throws ItemNotFoundException, MemberUnprivilegedException;


    Unit findOne(UUID id)
            throws ItemNotFoundException;

}
