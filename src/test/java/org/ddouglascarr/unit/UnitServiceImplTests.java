package org.ddouglascarr.unit;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.repositories.UnitRepository;
import org.ddouglascarr.services.UnitService;
import org.ddouglascarr.services.UnitServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UnitServiceImplTests
{
    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private UnitService unitService = new UnitServiceImpl();

    private Unit mockUnit2;
    private Unit mockUnit3;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockUnit2 = new Unit();
        mockUnit2.setId(new Long(2));
        mockUnit2.setName("Mock Unit 2");

        mockUnit3 = new Unit();
        mockUnit3.setId(new Long(3));
        mockUnit3.setName("Mock Unit 3");
    }

    @Test( expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfMemberNotFound() throws ItemNotFoundException
    {
        when(unitRepository.findOne(new Long(6))).thenReturn(null);
        Unit returnedUnit = unitService.findOne(new Long(6));
    }

    @Test
    public void findOneShouldReturnUnit() throws ItemNotFoundException
    {
        when(unitRepository.findOne(new Long(2))).thenReturn(mockUnit2);
        Unit returnedUnit = unitService.findOne(new Long(2));
        assertEquals(returnedUnit, mockUnit2);
    }
}
