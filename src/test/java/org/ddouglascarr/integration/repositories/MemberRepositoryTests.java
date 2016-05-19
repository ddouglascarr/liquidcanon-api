package org.ddouglascarr.integration.repositories;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.models.Member;
import org.ddouglascarr.repositories.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
import static org.ddouglascarr.utils.IntegrationTestConsts.*;

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

    @Test(expected = ItemNotFoundException.class)
    public void getOneByIdShouldThrowIfNotFound() throws Exception
    {
        memberRepository.findOneById(NON_EXISTANT_MEMBER_ID);
    }

    @Test
    public void getOneByIdShouldReturnMember() throws Exception
    {
        Member returnedMember = memberRepository.findOneById(POITRAS_MEMBER_ID);
        assertEquals(POITRAS_MEMBER_ID, returnedMember.getId());
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByUnitIdAndIdShouldThrowIfNotFound() throws Exception
    {
        memberRepository.findOneByUnitIdAndId(EARTH_UNIT_ID, KHORANA_MEMBER_ID);
    }

    @Test
    public void findOneByUnitIdAndIdShouldReturnMember() throws Exception
    {
        Member returnedMember = memberRepository
                .findOneByUnitIdAndId(EARTH_UNIT_ID, POITRAS_MEMBER_ID);
        assertEquals(POITRAS_MEMBER_ID, returnedMember.getId());
    }

    @Test(expected = ItemNotFoundException.class)
    public void findOneByLoginShouldThrowIfNotFound() throws Exception
    {
        memberRepository.findOneByLogin("nonexistant_unicorn");
    }

    @Test
    public void findOneByLoginShouldReturnMember() throws Exception
    {
        Member returnedMember = memberRepository.findOneByLogin("determined_poitras");
        assertNotNull(returnedMember);
        assertEquals(POITRAS_MEMBER_ID, returnedMember.getId());
    }

    @Test
    public void findByUnitIdShouldReturnListOfMember()
    {
        List<Member> returnedMembers = memberRepository.findByUnitId(EARTH_MOON_FEDERATION_UNIT_ID);
        assertEquals(15, returnedMembers.size());

        Member poitras = returnedMembers.stream()
                .filter(m -> POITRAS_MEMBER_ID.equals(m.getId()))
                .findFirst().get();
        assertNotNull(poitras);
        assertEquals(POITRAS_MEMBER_ID, poitras.getId());
    }

}
