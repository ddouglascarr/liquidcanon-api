package org.ddouglascarr.integration;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.models.Delegation;
import org.ddouglascarr.repositories.DelegationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

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
    private final Long HEISENBERG_MEMBER_ID = new Long(5);
    private final Long EARTH_UNIT_ID = new Long(2);

    @Test
    public void findOneUnitDelegationByTrusterIdShouldReturnDelegation()
            throws Exception
    {
        Delegation delegation = delegationRepository
                .findOneUnitDelegationByTrusterId(EARTH_UNIT_ID, HEISENBERG_MEMBER_ID);
        assertEquals(POITRAS_MEMBER_ID, delegation.getTrusteeId());
        assertEquals(new Long(13), delegation.getId());
    }
}
