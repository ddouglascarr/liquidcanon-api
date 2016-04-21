package org.ddouglascarr.services;

import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.UserDetailsImpl;
import org.ddouglascarr.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 17/04/16.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        Member member = memberRepository.findOneByLogin(login);
        List<String> memberRoles = new ArrayList<>();
        if (null == member) {
            throw new UsernameNotFoundException("No member present with login: " + login);
        } else {
            Boolean isAdmin = member.getAdmin();
            if (isAdmin) {
                memberRoles.add("ROLE_ADMIN");
            } else {
                memberRoles.add("ROLE_USER");
            }
            return new UserDetailsImpl(member, memberRoles);
        }
    }
}
