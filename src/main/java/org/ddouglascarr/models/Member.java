package org.ddouglascarr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="password_liquidcanon")
    @JsonIgnore
    private String password;
    private String login;

    private String name;
    private Boolean admin;
    private String notifyEmail;
    private Boolean active;
    private Date lastActivity;

    @ReadOnlyProperty
    @ManyToMany
    @JoinTable(
            name = "privilege",
            joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "id")
    )
    private List<Unit> units;

    public Member()
    {}

    public Member(Member member)
    {
        this.id = member.id;
        this.login = member.login;
        this.name = member.name;
        this.admin = member.admin;
        this.lastActivity = member.lastActivity;
        this.active = member.active;
        this.password = member.password;
    }


    // Getters and Setters
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getAdmin()
    {
        return admin;
    }

    public void setAdmin(Boolean admin)
    {
        this.admin = admin;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public List<Unit> getUnits()
    {
        return units;
    }

    public void setUnits(List<Unit> units)
    {
        this.units = units;
    }

    public Date getLastActivity()
    {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity)
    {
        this.lastActivity = lastActivity;
    }

    public String getNotifyEmail()
    {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail)
    {
        this.notifyEmail = notifyEmail;
    }
}
