package org.ddouglascarr.integration.controllers;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.hamcrest.Matchers.*;
import static org.ddouglascarr.utils.IntegrationTestConsts.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {LiquidcanonApplication.class, WebSecurityConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/liquid-canon.sql",
                "classpath:sql/test-world.sql"
        }
)
public class UnitsControllerTests
{

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception
    {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getUnitShould4xxIfUnitDoesNotExist() throws Exception
    {
        mockMvc.perform(get("/units/" + NON_EXISTENT_UNIT_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getUnitShould401IfMemberDoesNotHaveUnitReadPrivilegeAndUnitIsPrivate()
            throws Exception
    {
        mockMvc.perform(get("/units/" + MARS_UNIT_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getUnitShouldSucceedIfMemberDoesNotHaveUnitPrivilegeAndUnitIsPublic()
            throws Exception
    {
        mockMvc.perform(get("/units/" + MOON_UNIT_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isOk());
    }

    @Test
    public void getUnitShouldReturnUnit() throws Exception
    {
        mockMvc.perform(get("/units/" + EARTH_MOON_FEDERATION_UNIT_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(EARTH_MOON_FEDERATION_UNIT_ID.intValue())))
                .andExpect(jsonPath("$.parentId", is(SOLAR_SYSTEM_UNIT_ID.intValue())))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.name", is(EARTH_MOON_FEDERATION_UNIT_NAME)))
                .andExpect(jsonPath("$.description", isEmptyOrNullString()))
                .andExpect(jsonPath("$.memberCount", isEmptyOrNullString()))    // TODO
                .andExpect(jsonPath("$.areas", isEmptyOrNullString()))          // TODO
                .andExpect(jsonPath("$.*", hasSize(7)));
    }

}
