package org.ddouglascarr.query.models;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class UnitPermission
{
    @Id
    private UUID id;

    private UUID unitId;
    private Boolean publicRead;


    // getters and setters

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getUnitId()
    {
        return unitId;
    }

    public void setUnitId(UUID unitId)
    {
        this.unitId = unitId;
    }

    public Boolean getPublicRead()
    {
        return publicRead;
    }

    public void setPublicRead(Boolean publicRead)
    {
        this.publicRead = publicRead;
    }
}
