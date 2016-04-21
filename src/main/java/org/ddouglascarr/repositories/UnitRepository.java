package org.ddouglascarr.repositories;

import org.ddouglascarr.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long>
{
    Unit findOneById(Long id);
    Unit findOneByName(String name);
}
