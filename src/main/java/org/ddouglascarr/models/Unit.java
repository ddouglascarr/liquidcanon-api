package org.ddouglascarr.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;


public class Unit
{
    @Id
    private Long id;

    private Long parentId;

    private Boolean active;
    private String name;
    private String description;

    private Long memberCount;

    @ReadOnlyProperty
    private List<Area> areas;

    // Getters and Setters

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
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

    public Long getMemberCount()
    {
        return memberCount;
    }

    public void setMemberCount(Long memberCount)
    {
        this.memberCount = memberCount;
    }

    public List<Area> getAreas()
    {
        return areas;
    }

    public void setAreas(List<Area> areas)
    {
        this.areas = areas;
    }
}
