package org.ddouglascarr.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Privilege;

public interface PrivilegeRepository
{
    Privilege findOneByMemberIdAndUnitId(Long memberId, Long unitId)
            throws ItemNotFoundException;
}
