package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Area;

import java.util.List;

public interface AreaService
{
    Area findOne(Long memberId, Long id)
            throws ItemNotFoundException, MemberUnprivilegedException;

    default Area findOne(Long id) throws ItemNotFoundException
    {
        throw new RuntimeException("method removed");
    }

   default Area findOneByUnitId(Long unitId, Long id) throws ItemNotFoundException
   {
       throw new RuntimeException("method removed");
   }
    Area findOneByUnitId(Long memberid, Long unitId, Long id)
            throws ItemNotFoundException, MemberUnprivilegedException;

    default List<Area> findByUnitId(Long unitId)
    {
        throw new RuntimeException("method removed");
    }

    List<Area> findByUnitId(Long memberId, Long unitId)
            throws ItemNotFoundException, MemberUnprivilegedException;
}
