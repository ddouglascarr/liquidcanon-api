package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Unit;

import java.util.UUID;

public interface UnitService
{
    Unit findOne(UUID memberId, UUID id)
            throws ItemNotFoundException, MemberUnprivilegedException;


    default Unit findOne(UUID id)
            throws ItemNotFoundException
    {
        throw new RuntimeException("Method removed");
    }

}
