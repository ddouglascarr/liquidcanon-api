package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Privilege.PrivilegeId>
{
    Privilege findOneByMemberIdAndUnitId(Long memberId, Long unitId);
}
