package org.ddouglascarr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(value = Privilege.PrivilegeId.class)
public class Privilege
{
    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Id
    @Column(name = "unit_id")
    private Long unitId;

    private Boolean unit_manager;
    private Boolean area_manager;
    private Boolean member_manager;
    private Boolean initiative_right;
    private Boolean voting_right;
    private Boolean polling_right;

    // Id Class
    public static class PrivilegeId implements Serializable {
        private Long memberId;
        private Long unitId;

        PrivilegeId() {}

        PrivilegeId(Long memberId, Long unitId)
        {
            this.memberId = memberId;
            this.unitId = unitId;
        }

        public Long getMemberId()
        {
            return memberId;
        }

        public void setMemberId(Long memberId)
        {
            this.memberId = memberId;
        }

        public Long getUnitId()
        {
            return unitId;
        }

        public void setUnitId(Long unitId)
        {
            this.unitId = unitId;
        }
    }

    // Getters and setters

    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getUnitId()
    {
        return unitId;
    }

    public void setUnitId(Long unitId)
    {
        this.unitId = unitId;
    }

    public Boolean getUnit_manager()
    {
        return unit_manager;
    }

    public void setUnit_manager(Boolean unit_manager)
    {
        this.unit_manager = unit_manager;
    }

    public Boolean getArea_manager()
    {
        return area_manager;
    }

    public void setArea_manager(Boolean area_manager)
    {
        this.area_manager = area_manager;
    }

    public Boolean getMember_manager()
    {
        return member_manager;
    }

    public void setMember_manager(Boolean member_manager)
    {
        this.member_manager = member_manager;
    }

    public Boolean getInitiative_right()
    {
        return initiative_right;
    }

    public void setInitiative_right(Boolean initiative_right)
    {
        this.initiative_right = initiative_right;
    }

    public Boolean getVoting_right()
    {
        return voting_right;
    }

    public void setVoting_right(Boolean voting_right)
    {
        this.voting_right = voting_right;
    }

    public Boolean getPolling_right()
    {
        return polling_right;
    }

    public void setPolling_right(Boolean polling_right)
    {
        this.polling_right = polling_right;
    }
}
