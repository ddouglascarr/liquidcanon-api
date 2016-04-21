package org.ddouglascarr.services;

import org.ddouglascarr.models.Member;

/**
 * Created by daniel on 17/04/16.
 */
public interface MemberService
{
    Member findOne(Long id);
    Member findOneByLogin(String login);
}
