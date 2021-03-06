package org.ddouglascarr.integration.query.repositories;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Privilege;
import org.ddouglascarr.query.repositories.PrivilegeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/test-world.sql"
        })
public class PrivilegeRepositoryImplTests
{
    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test(expected = ItemNotFoundException.class)
    public void findOneByMemberIdAndUnitIdShouldThrowIfDoesNotExist()
            throws Exception
    {
        privilegeRepository.findOneByMemberIdAndUnitId(POITRAS_MEMBER_ID, MARS_UNIT_ID);
    }

    @Test
    public void findOneByMemberIdAndUnitIdShouldReturnPrivilege()
            throws Exception
    {
        Privilege privilege = privilegeRepository
                .findOneByMemberIdAndUnitId(POITRAS_MEMBER_ID, EARTH_UNIT_ID);
        assertEquals(POITRAS_MEMBER_ID, privilege.getMemberId());
    }
}
