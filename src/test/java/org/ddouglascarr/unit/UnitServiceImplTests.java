package org.ddouglascarr.unit;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Unit;
import org.ddouglascarr.repositories.UnitRepository;
import org.ddouglascarr.services.PrivilegeService;
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

    @Mock
    private PrivilegeService privilegeService;

    @InjectMocks
    private UnitService unitService = new UnitServiceImpl();

    private Unit mockUnit2;
    private Unit mockUnit3;

    private static Long UNIT_2_ID = new Long(2);
    private static Long UNIT_3_ID = new Long(2);
    private static Long MEMBER_ID = new Long(32);

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
        when(unitRepository.findOneById(UNIT_2_ID)).thenReturn(null);
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
        when(unitRepository.findOneById(UNIT_2_ID)).thenReturn(mockUnit2);
        Unit returnedUnit = unitService.findOne(MEMBER_ID, UNIT_2_ID);
        assertEquals(returnedUnit, mockUnit2);
    }
}
