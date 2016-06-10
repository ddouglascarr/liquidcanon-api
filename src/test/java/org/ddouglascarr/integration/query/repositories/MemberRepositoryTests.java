package org.ddouglascarr.integration.query.repositories;

import org.apache.commons.lang.time.DateUtils;
import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.exceptions.ItemNotFoundException;
import org.ddouglascarr.query.models.Member;
import org.ddouglascarr.query.repositories.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        assertEquals(6, returnedMembers.size());

        Member poitras = returnedMembers.stream()
                .filter(m -> POITRAS_MEMBER_ID.equals(m.getId()))
                .findFirst().get();
        assertNotNull(poitras);
        assertEquals(POITRAS_MEMBER_ID, poitras.getId());
    }

    @Test
    public void isAtLeastOneAdminMemberShouldReturnTrueIfAdminExists()
    {
        assertEquals(true, memberRepository.isAtLeastOneAdminMember());
    }

    @Test
    public void createShouldCreateAdminMember() throws Exception
    {
        Member newAdminMember = new Member();
        UUID newId = UUID.randomUUID();
        Date now = new Date();
        newAdminMember.setId(newId);
        newAdminMember.setAdmin(true);
        newAdminMember.setName("New Admin");
        newAdminMember.setLogin("newadmin");
        newAdminMember.setPassword("password1234");
        newAdminMember.setActive(true);
        newAdminMember.setActivated(now);
        newAdminMember.setLastActivity(now);
        memberRepository.create(newAdminMember);
        Member createdMember = memberRepository.findOneById(newId);
        assertNotNull(createdMember);
        assertEquals(newId, createdMember.getId());
        assertEquals(true, createdMember.getAdmin());
        assertEquals("New Admin", createdMember.getName());
        assertEquals("newadmin", createdMember.getLogin());
        assertEquals(true, createdMember.getActive());
        assertEquals(now.toInstant(), createdMember.getActivated().toInstant());
        assertTrue(DateUtils.isSameDay(now, createdMember.getLastActivity()));
    }

}
