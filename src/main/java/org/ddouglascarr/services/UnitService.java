package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Unit;

public interface UnitService
{
    Unit findOne(Long memberId, Long id)
            throws ItemNotFoundException, MemberUnprivilegedException;


    default Unit findOne(Long id)
            throws ItemNotFoundException
    {
        throw new RuntimeException("Method removed");
    }

}
