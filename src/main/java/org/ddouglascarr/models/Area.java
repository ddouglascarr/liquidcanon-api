package org.ddouglascarr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Area
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "unit_id")
    private Long unitId;

    private Boolean active;
    private String name;
    private String description;
    private String external_reference;
    private Long direct_member_count;
    private Long member_weight;


    // Getters and Setters

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

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getExternal_reference()
    {
        return external_reference;
    }

    public void setExternal_reference(String external_reference)
    {
        this.external_reference = external_reference;
    }

    public Long getDirect_member_count()
    {
        return direct_member_count;
    }

    public void setDirect_member_count(Long direct_member_count)
    {
        this.direct_member_count = direct_member_count;
    }

    public Long getMember_weight()
    {
        return member_weight;
    }

    public void setMember_weight(Long member_weight)
    {
        this.member_weight = member_weight;
    }
}
