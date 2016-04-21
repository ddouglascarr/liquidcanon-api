package org.ddouglascarr.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 21/04/16.
 */
public class UserDetailsImpl extends Member implements UserDetails
{
    private List<String> memberRoles;

    public UserDetailsImpl(Member member, List<String> memberRoles)
    {
        super(member);
        this.memberRoles = memberRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getUsername()
    {
        return super.getLogin();
    }

    @Override
    public Long getId()
    {
        return super.getId();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return super.getActive();
    }


}
