package org.ddouglascarr.models;

import java.math.BigInteger;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Unit
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    private Boolean active;
    private String name;
    private String description;

    @Column(name = "member_count")
    private Long memberCount;

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
}
