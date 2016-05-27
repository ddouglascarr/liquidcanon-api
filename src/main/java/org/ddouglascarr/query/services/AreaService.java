package org.ddouglascarr.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Area;

import java.util.List;
import java.util.UUID;

public interface AreaService
{
    Area findOne(UUID memberId, UUID id)
            throws ItemNotFoundException, MemberUnprivilegedException;

    default Area findOne(UUID id) throws ItemNotFoundException
    {
        throw new RuntimeException("method removed");
    }

   default Area findOneByUnitId(UUID unitId, UUID id) throws ItemNotFoundException
   {
       throw new RuntimeException("method removed");
   }
    Area findOneByUnitId(UUID memberId, UUID unitId, UUID id)
            throws ItemNotFoundException, MemberUnprivilegedException;

    default List<Area> findByUnitId(UUID unitId)
    {
        throw new RuntimeException("method removed");
    }

    List<Area> findByUnitId(UUID memberId, UUID unitId)
            throws ItemNotFoundException, MemberUnprivilegedException;
}
