package org.ddouglascarr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unit_permissions")
public class UnitPermission
{
    @Id
    private Long id;

    @Column(name = "unit_id")
    private Long unitId;

    @Column(name = "public_read")
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
