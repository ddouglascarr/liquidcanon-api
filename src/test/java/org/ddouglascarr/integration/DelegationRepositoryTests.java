package org.ddouglascarr.integration;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.enums.DelegationScope;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.repositories.DelegationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/liquid-canon.sql",
                "classpath:sql/test-world.sql"
        }
)
public class DelegationRepositoryTests
{
    @Autowired
    private DelegationRepository delegationRepository;

    private final Long POITRAS_MEMBER_ID = new Long(1);
    private final Long HUGLE_MEMBER_ID = new Long(2);
    private final Long ALMEIDA_MEMBER_ID = new Long(3);
    private final Long HEISENBERG_MEMBER_ID = new Long(5);
    private final Long BABBAGE_MEMBER_ID = new Long(6);
    private final Long CARSON_MEMBER_ID = new Long(13);
    private final Long KHORANA_MEMBER_ID = new Long(19);
    private final Long SOLAR_SYSTEM_UNIT_ID = new Long(1);
    private final Long EARTH_UNIT_ID = new Long(2);
    private final Long MARS_UNIT_ID = new Long(5);
    private final Long ALIEN_AFFAIRS_AREA_ID = new Long(7);

    @Test
    public void findOneUnitDelegationByTrusterIdShouldReturnDelegation()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findUnitDelegationByTrusterId(EARTH_UNIT_ID, HEISENBERG_MEMBER_ID);
        assertEquals(POITRAS_MEMBER_ID, delegation.getTrusteeId());
        assertEquals(new Long(13), delegation.getId());
    }

    @Test
    public void findOneUnitDelegationByTrusterIdShouldReturnNullIfNotDelegation()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findUnitDelegationByTrusterId(EARTH_UNIT_ID, BABBAGE_MEMBER_ID);
        assertNull(delegation);
    }

    @Test
    public void findUnitDelegationsByTrusteeIdShouldReturnListOfDelegations()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findUnitDelegationsByTrusteeId(SOLAR_SYSTEM_UNIT_ID, POITRAS_MEMBER_ID);
        assertEquals(4, delegations.size());
    }

    @Test
    public void findAreaDelegationByTrusterId()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findAreaDelegationByTrusterId(SOLAR_SYSTEM_UNIT_ID,
                        ALIEN_AFFAIRS_AREA_ID,
                        KHORANA_MEMBER_ID);
        assertEquals(new Long(16), delegation.getId());
        assertEquals(HEISENBERG_MEMBER_ID, delegation.getTrusteeId());
    }

    @Test
    public void findAreaDelegationByTrusterIdShouldReturnNullIfNoDelegation()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findAreaDelegationByTrusterId(
                        SOLAR_SYSTEM_UNIT_ID,
                        ALIEN_AFFAIRS_AREA_ID,
                        HEISENBERG_MEMBER_ID);
        assertNull(delegation);
    }

    @Test
    public void findAreaDelegationByTrusterIdShouldReturnNullIfWrongUnitIdForArea()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findAreaDelegationByTrusterId(
                        EARTH_UNIT_ID,
                        ALIEN_AFFAIRS_AREA_ID,
                        KHORANA_MEMBER_ID);
        assertNull(delegation);
    }

    @Test
    public void findAreaDelegationsByTrusteeIdShouldReturnEmptyListIfWrongUnitForArea()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findAreaDelegationsByTrusteeId(
                        EARTH_UNIT_ID,
                        ALIEN_AFFAIRS_AREA_ID,
                        CARSON_MEMBER_ID);
        assertEquals(0, delegations.size());
    }

    @Test
    public void findAreaDelegationsByTrusteeIdShouldReturnListOfDelegations()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findAreaDelegationsByTrusteeId(
                        SOLAR_SYSTEM_UNIT_ID,
                        ALIEN_AFFAIRS_AREA_ID,
                        CARSON_MEMBER_ID);
        assertEquals(2, delegations.size());

        Delegation almeidaDelegation = delegations.stream()
                .filter(d -> ALMEIDA_MEMBER_ID.equals(d.getTrusterId()))
                .findFirst().get();
        assertEquals(new Long(14), almeidaDelegation.getId());
    }

    @Test
    public void findAreaDelegationsByUnitIdAndTrusterIdShouldReturnListOfDelegations()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findAreaDelegationsByUnitIdAndTrusterId(SOLAR_SYSTEM_UNIT_ID, ALMEIDA_MEMBER_ID);
        assertEquals(2, delegations.size());
    }

    @Test
    public void findByTrusterIdShouldReturnEmptyListIfTrusterIsNotPrivileged()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findByTrusterId(MARS_UNIT_ID, ALMEIDA_MEMBER_ID);
        assertEquals(0, delegations.size());
    }

    @Test
    public void findByTrusterIdShouldReturnListOfDelegations()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findByTrusterId(SOLAR_SYSTEM_UNIT_ID, ALMEIDA_MEMBER_ID);
        assertEquals(3, delegations.size());

        Delegation solarSystemDelegation = delegations.stream()
                .filter(d -> DelegationScope.unit.equals(d.getScope()))
                .findFirst().get();
        assertEquals(ALMEIDA_MEMBER_ID, solarSystemDelegation.getTrusterId());
        assertEquals(POITRAS_MEMBER_ID, solarSystemDelegation.getTrusteeId());

        Delegation alienAffairsDelegation = delegations.stream()
                .filter(d -> d.getAreaId().equals(ALIEN_AFFAIRS_AREA_ID))
                .findFirst().get();
        assertEquals(ALMEIDA_MEMBER_ID, alienAffairsDelegation.getTrusterId());
        assertEquals(CARSON_MEMBER_ID, alienAffairsDelegation.getTrusteeId());

    }


}
