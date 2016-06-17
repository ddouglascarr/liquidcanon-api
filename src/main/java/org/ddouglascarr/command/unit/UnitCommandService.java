package org.ddouglascarr.command.unit;

import org.ddouglascarr.exceptions.ConflictException;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;

import java.util.UUID;

public interface UnitCommandService
{
    UUID create(UUID requestingMemberId, UUID parentId, String name, String description)
            throws MemberUnprivilegedException, ItemNotFoundException;
}
