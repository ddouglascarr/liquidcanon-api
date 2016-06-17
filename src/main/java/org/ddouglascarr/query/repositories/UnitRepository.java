package org.ddouglascarr.query.repositories;

import org.ddouglascarr.query.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UnitRepository extends JpaRepository<Unit, UUID>
{
    Unit findOneByName(String name);
}
