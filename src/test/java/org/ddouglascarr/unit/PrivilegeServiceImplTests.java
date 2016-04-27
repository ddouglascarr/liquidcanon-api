package org.ddouglascarr.unit;

import org.ddouglascarr.exceptions.MemberUnprivilegedException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.models.Privilege;
import org.ddouglascarr.models.UnitPermission;
import org.ddouglascarr.repositories.PrivilegeRepository;
import org.ddouglascarr.repositories.UnitPermissionRepository;
import org.ddouglascarr.services.PrivilegeService;
import org.ddouglascarr.services.PrivilegeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class PrivilegeServiceImplTests
{
    @Mock
    private PrivilegeRepository privilegeRepository;

    @Mock
    private UnitPermissionRepository unitPermissionRepository;

    @InjectMocks
    private PrivilegeService privilegeService = new PrivilegeServiceImpl();

    private Privilege mockPrivilege;
    private UnitPermission mockUnitPermission;

    private static Long MOCK_UNIT_ID = new Long(51);
    private static Long MOCK_MEMBER_ID = new Long(12);

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockPrivilege = new Privilege();
        mockPrivilege.setUnitId(MOCK_UNIT_ID);
        mockPrivilege.setMemberId(MOCK_MEMBER_ID);

        mockUnitPermission = new UnitPermission();
        mockUnitPermission.setId(new Long(1));
        mockUnitPermission.setUnitId(new Long(51));
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitReadPrivilegeShouldThrowIfNoPublicReadOrPrivilege()
            throws MemberUnprivilegedException
    {
        mockUnitPermission.setPublicRead(false);
        when(unitPermissionRepository.findOneByUnitId(new Long(51))).thenReturn(mockUnitPermission);
        when(privilegeRepository.findOneByMemberIdAndUnitId(new Long(12), new Long(51)))
                .thenReturn(null);
        privilegeService.assertUnitReadPrivilege(new Long(12), new Long(51));
    }

    @Test
    public void assertUnitReadPrivilegeShouldNotThrowOnPrivilegeWithoutPublicRead()
            throws MemberUnprivilegedException
    {
        mockUnitPermission.setPublicRead(false);
        when(unitPermissionRepository.findOneByUnitId(new Long(51))).thenReturn(mockUnitPermission);
        when(privilegeRepository.findOneByMemberIdAndUnitId(new Long(12), new Long(51)))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitReadPrivilege(new Long(12), new Long(51));
    }

    @Test
    public void assertUnitReadPrivilegeShouldNotThrowOnPublicRead()
            throws MemberUnprivilegedException
    {
        mockUnitPermission.setPublicRead(true);
        when(unitPermissionRepository.findOneByUnitId(new Long(51))).thenReturn(mockUnitPermission);
        when(privilegeRepository.findOneByMemberIdAndUnitId(new Long(12), new Long(51)))
                .thenReturn(null);
        privilegeService.assertUnitReadPrivilege(new Long(12), new Long(51));
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitVotingPrivilegeShouldThrowIfNoPrivileges()
            throws MemberUnprivilegedException
    {
        when(privilegeRepository.findOneByMemberIdAndUnitId(new Long(12), new Long(51)))
                .thenReturn(null);
        privilegeService.assertUnitVotingPrivilege(new Long(12), new Long(51));
    }

    @Test(expected = MemberUnprivilegedException.class)
    public void assertUnitVotingPrivilegeShouldThrowIfNoVotingPrivilege()
            throws MemberUnprivilegedException
    {
        mockPrivilege.setVoting_right(false);
        when(privilegeRepository.findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitVotingPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }

    @Test
    public void assertUnitVotingPrivilegeShouldNotThrowIfVotingPrivilege()
            throws MemberUnprivilegedException
    {
        mockPrivilege.setVoting_right(true);
        when(privilegeRepository.findOneByMemberIdAndUnitId(MOCK_MEMBER_ID, MOCK_UNIT_ID))
                .thenReturn(mockPrivilege);
        privilegeService.assertUnitVotingPrivilege(MOCK_MEMBER_ID, MOCK_UNIT_ID);
    }
}
