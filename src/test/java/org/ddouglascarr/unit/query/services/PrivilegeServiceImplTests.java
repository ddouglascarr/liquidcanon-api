package org.ddouglascarr.unit.query.services;

import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.query.models.Privilege;
import org.ddouglascarr.query.models.Unit;
import org.ddouglascarr.query.repositories.PrivilegeRepository;
import org.ddouglascarr.query.repositories.UnitRepository;
import org.ddouglascarr.query.services.PrivilegeService;
import org.ddouglascarr.query.services.PrivilegeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class PrivilegeServiceImplTests
{
    @Mock
    private PrivilegeRepository privilegeRepository;

    @Mock
    UnitRepository unitRepository;

    @Mock
    Unit mockUnit;

    @InjectMocks
    private PrivilegeService privilegeService = new PrivilegeServiceImpl();

    private Privilege mockPrivilege;

    private static UUID MOCK_UNIT_ID = UUID.randomUUID();
    private static UUID MOCK_MEMBER_ID = UUID.randomUUID();

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        mockPrivilege = new Privilege();
        mockPrivilege.setUnitId(MOCK_UNIT_ID);
        mockPrivilege.setMemberId(MOCK_MEMBER_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitReadPrivilegeShouldThrowIfNoPublicReadOrPrivilege()
            throws Exception
    {
        when(mockUnit.getPublicRead()).thenReturn(false);
        when(unitRepository.findOne(MOCK_UNIT_ID)).thenReturn(mockUnit);
        doThrow(new ItemNotFoundException()).when(privilegeRepository)
                .findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID);
        privilegeService.assertUnitReadPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitReadPrivilegeShouldThrowIfUnitPermissionMissingAndNoPrivilege()
            throws Exception
    {
        when(mockUnit.getPublicRead()).thenReturn(false);
        when(unitRepository.findOne(MOCK_UNIT_ID)).thenReturn(mockUnit);
        doThrow(new ItemNotFoundException()).when(privilegeRepository)
                .findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID);
        privilegeService.assertUnitReadPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test
    public void assertUnitReadPrivilegeShouldNotThrowIfUnitPermisionMissingButPrivilegeExists()
            throws Exception
    {
        when(mockUnit.getPublicRead()).thenReturn(false);
        when(unitRepository.findOne(MOCK_UNIT_ID)).thenReturn(mockUnit);
        when(privilegeRepository.findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitReadPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test
    public void assertUnitReadPrivilegeShouldNotThrowOnPrivilegeWithoutPublicRead()
            throws Exception
    {
        when(mockUnit.getPublicRead()).thenReturn(false);
        when(unitRepository.findOne(MOCK_UNIT_ID)).thenReturn(mockUnit);
        when(privilegeRepository.findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitReadPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test
    public void assertUnitReadPrivilegeShouldNotThrowOnPublicRead()
            throws Exception
    {
        when(mockUnit.getPublicRead()).thenReturn(true);
        when(unitRepository.findOne(MOCK_UNIT_ID)).thenReturn(mockUnit);
        doThrow(new ItemNotFoundException()).when(privilegeRepository)
                .findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID);
        privilegeService.assertUnitReadPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitVotingPrivilegeShouldThrowIfNoPrivileges()
            throws Exception
    {
        doThrow(new ItemNotFoundException()).when(privilegeRepository)
                .findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID);
        privilegeService.assertUnitVotingPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitVotingPrivilegeShouldThrowIfNoVotingPrivilege()
            throws Exception
    {
        mockPrivilege.setVotingRight(false);
        when(privilegeRepository.findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitVotingPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test
    public void assertUnitVotingPrivilegeShouldNotThrowIfVotingPrivilege()
            throws Exception
    {
        mockPrivilege.setVotingRight(true);
        when(privilegeRepository.findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitVotingPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }
}
