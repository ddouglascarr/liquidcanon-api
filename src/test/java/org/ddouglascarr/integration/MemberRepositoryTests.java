package org.ddouglascarr.integration;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.repositories.MemberRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(LiquidcanonApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/liquid-canon.sql",
                "classpath:sql/test-world.sql"
        })
public class MemberRepositoryTests
{
    @Autowired
    private MemberRepository memberRepository;

    private final Long POITRAS_MEMBER_ID = new Long(1);
    private final Long BABBAGE_MEMBER_ID = new Long(6);
    private final Long KHORANA_MEMBER_ID = new Long(19);
    private final Long SOLAR_SYSTEM_UNIT_ID = new Long(1);
    private final Long EARTH_UNIT_ID = new Long(2);
    private final Long MARS_UNIT_ID = new Long(5);


}
