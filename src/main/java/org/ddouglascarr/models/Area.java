package org.ddouglascarr.models;


import org.springframework.data.annotation.Id;

public class Area
{
    @Id
    private Long id;
    private Long unitId;
    private Boolean active;
    private String name;
    private String description;
    private String externalReference;
    private Long directMemberCount;
    private Long memberWeight;


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

    public String getExternalReference()
    {
        return externalReference;
    }

    public void setExternalReference(String externalReference)
    {
        this.externalReference = externalReference;
    }

    public Long getDirectMemberCount()
    {
        return directMemberCount;
    }

    public void setDirectMemberCount(Long directMemberCount)
    {
        this.directMemberCount = directMemberCount;
    }

    public Long getMemberWeight()
    {
        return memberWeight;
    }

    public void setMemberWeight(Long memberWeight)
    {
        this.memberWeight = memberWeight;
    }
}
