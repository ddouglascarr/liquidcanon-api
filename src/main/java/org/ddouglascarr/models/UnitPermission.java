package org.ddouglascarr.models;

import org.springframework.data.annotation.Id;

public class UnitPermission
{
    @Id
    private Long id;

    private Long unitId;
    private Boolean publicRead;


    // getters and setters

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUnitId()
    {
        return unitId;
    }

    public void setUnitId(Long unitId)
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
