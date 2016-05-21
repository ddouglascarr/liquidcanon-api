package org.ddouglascarr.commandhandlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.ddouglascarr.commands.AddMember;
import org.ddouglascarr.entities.UnitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddMemberHandler
{
    @Autowired
    private Repository repository;

    @CommandHandler
    public void handle(AddMember addMemberCommand)
    {
        UnitEntity unitEntity = (UnitEntity) repository.load(addMemberCommand.getUnitId());
    }
}
