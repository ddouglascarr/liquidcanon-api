package org.ddouglascarr.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService
{
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Unit findOne(Long id) throws ItemNotFoundException
    {
        Unit unit = unitRepository.findOne(id);
        if (null == unit) throw new ItemNotFoundException();
        return unit;
    }
}
