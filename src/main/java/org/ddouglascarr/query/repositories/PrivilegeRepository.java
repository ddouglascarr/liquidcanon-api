package org.ddouglascarr.query.repositories;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Privilege;

import java.util.UUID;

public interface PrivilegeRepository
{
    Privilege findOneByMemberIdAndUnitId(UUID memberId, UUID unitId)
            throws ItemNotFoundException;
}
