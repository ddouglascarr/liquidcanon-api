package org.ddouglascarr.query.models;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Unit
{
    @Id
    @Type(type="pg-uuid")
    private UUID id;

    @Type(type="pg-uuid")
    private UUID parentId;

    private Boolean active;
    private String name;
    private String description;

    private Long memberCount;
    private Boolean publicRead;


    // Getters and Setters

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getParentId()
    {
        return parentId;
    }

    public void setParentId(UUID parentId)
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

    public Boolean getPublicRead()
    {
        return publicRead;
    }

    public void setPublicRead(Boolean publicRead)
    {
        this.publicRead = publicRead;
    }
}
