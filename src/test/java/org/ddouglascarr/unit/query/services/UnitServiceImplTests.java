package org.ddouglascarr.unit.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.repositories.UnitRepository;
import org.ddouglascarr.query.services.PrivilegeService;
import org.ddouglascarr.query.services.UnitService;
import org.ddouglascarr.query.services.UnitServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UnitServiceImplTests
{
    @Mock
    private UnitRepository unitRepository;

    @Mock
    private PrivilegeService privilegeService;

    @InjectMocks
    private UnitService unitService = new UnitServiceImpl();

    private Unit mockUnit2;
    private Unit mockUnit3;

    private static UUID UNIT_2_ID = UUID.randomUUID();
    private static UUID UNIT_3_ID = UUID.randomUUID();
    private static UUID MEMBER_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockUnit2 = new Unit();
        mockUnit2.setId(UNIT_2_ID);
        mockUnit2.setName("Mock Unit 2");

        mockUnit3 = new Unit();
        mockUnit3.setId(UNIT_3_ID);
        mockUnit3.setName("Mock Unit 3");
    }

    @Test( expected = ItemNotFoundException.class)
    public void findOneShouldThrowIfUnitNotFound()
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        when(unitRepository.findOne(UNIT_2_ID)).thenReturn(null);
        Unit returnedUnit = unitService.findOne(MEMBER_ID, UNIT_2_ID);
    }

    @Test( expected = MemberUnprivilegedException.class)
    public void findOneShouldThrowIfMemberNotReadPrivileged()
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        doThrow(new MemberUnprivilegedException()).when(privilegeService)
                .assertUnitReadPrivilege(MEMBER_ID, UNIT_2_ID);
        Unit returnedUnit = unitService.findOne(MEMBER_ID, UNIT_2_ID);
    }

    @Test()
    public void findOneShouldReturnUnit()
            throws ItemNotFoundException, MemberUnprivilegedException
    {
        when(unitRepository.findOne(UNIT_2_ID)).thenReturn(mockUnit2);
        Unit returnedUnit = unitService.findOne(MEMBER_ID, UNIT_2_ID);
        assertEquals(returnedUnit, mockUnit2);
    }
}
