package org.ddouglascarr.integration.query.repositories;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.enums.DelegationScope;
import org.ddouglascarr.query.models.Delegation;
import org.ddouglascarr.query.repositories.DelegationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/test-world.sql"
        }
)
public class DelegationRepositoryTests
{
    @Autowired
    private DelegationRepository delegationRepository;

    @Test
    public void findOneUnitDelegationByTrusterIdShouldReturnDelegation()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findUnitDelegationByTrusterId(EARTH_MOON_FEDERATION_UNIT_ID, HEISENBERG_MEMBER_ID);
        assertEquals(POITRAS_MEMBER_ID, delegation.getTrusteeId());
        assertEquals(HEISENBERG_MEMBER_ID, delegation.getTrusterId());
    }

    @Test
    public void findOneUnitDelegationByTrusterIdShouldReturnNullIfNotDelegation()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findUnitDelegationByTrusterId(EARTH_MOON_FEDERATION_UNIT_ID, BABBAGE_MEMBER_ID);
        assertNull(delegation);
    }

    @Test
    public void findUnitDelegationsByTrusteeIdShouldReturnListOfDelegations()
            throws Exception
    {
        List<Delegation> delegations = delegationRepository
                .findUnitDelegationsByTrusteeId(SOLAR_SYSTEM_UNIT_ID, POITRAS_MEMBER_ID);
        assertEquals(3, delegations.size());
    }

    @Test
    public void findAreaDelegationByTrusterId()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findAreaDelegationByTrusterId(SOLAR_SYSTEM_UNIT_ID,
                        ALIEN_AFFAIRS_AREA_ID,
                        KHORANA_MEMBER_ID);
        assertEquals(HEISENBERG_MEMBER_ID, delegation.getTrusteeId());
        assertEquals(KHORANA_MEMBER_ID, delegation.getTrusterId());
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
                        EARTH_MOON_FEDERATION_UNIT_ID,
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
                        EARTH_MOON_FEDERATION_UNIT_ID,
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
                        HEISENBERG_MEMBER_ID);
        assertEquals(2, delegations.size());

        Delegation almeidaDelegation = delegations.stream()
                .filter(d -> ALMEIDA_MEMBER_ID.equals(d.getTrusterId()))
                .findFirst().get();
        assertEquals(ALMEIDA_MEMBER_ID, almeidaDelegation.getTrusterId());
        assertEquals(HEISENBERG_MEMBER_ID, almeidaDelegation.getTrusteeId());
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
        assertEquals(HEISENBERG_MEMBER_ID, alienAffairsDelegation.getTrusteeId());

    }


}
