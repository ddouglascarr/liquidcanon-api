package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PrivilegeRepository
{
    Privilege findOneByMemberIdAndUnitId(Long memberId, Long unitId);
}
