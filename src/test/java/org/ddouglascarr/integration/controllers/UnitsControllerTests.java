package org.ddouglascarr.integration.controllers;

import org.ddouglascarr.LiquidcanonApplication;
import org.ddouglascarr.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.hamcrest.Matchers.*;
import static org.ddouglascarr.testutils.IntegrationTestConsts.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {LiquidcanonApplication.class, WebSecurityConfig.class})
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/test-world.sql"
        })
public class UnitsControllerTests
{
    @Rule
    public RestDocumentation restDocumentation =
            new RestDocumentation("target/generated-snippets");

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception
    {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .apply(documentationConfiguration(this.restDocumentation))
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
                .andExpect(status().isUnauthorized())
                .andDo(document("units/get"));
    }

    @Test
    public void getUnitShouldSucceedIfMemberDoesNotHaveUnitPrivilegeAndUnitIsPublic()
            throws Exception
    {
        mockMvc.perform(get("/units/" + MOON_UNIT_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(7)));
    }

    @Test
    public void getUnitShouldReturnUnit() throws Exception
    {
        mockMvc.perform(get("/units/{unitId}", EARTH_MOON_FEDERATION_UNIT_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(EARTH_MOON_FEDERATION_UNIT_ID.toString())))
                .andExpect(jsonPath("$.parent_id", is(SOLAR_SYSTEM_UNIT_ID.toString())))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.name", is(EARTH_MOON_FEDERATION_UNIT_NAME)))
                .andExpect(jsonPath("$.description", isEmptyOrNullString()))
                .andExpect(jsonPath("$.member_count", isEmptyOrNullString()))    // TODO
                .andExpect(jsonPath("$.public_read", is(true)))
                .andExpect(jsonPath("$.*", hasSize(7)))
                .andDo(document("units/get", responseFields(
                        fieldWithPath("id")
                                .type(JsonFieldType.STRING)
                                .description("Primary key"),
                        fieldWithPath("parent_id")
                                .type(JsonFieldType.STRING)
                                .description("Referencing the parent unit (unset/null if unit is in root level)"),
                        fieldWithPath("active")
                                .type(JsonFieldType.BOOLEAN)
                                .description("New issues can be created in the unit"),
                        fieldWithPath("name")
                                .type(JsonFieldType.STRING)
                                .description("Name of the unit"),
                        fieldWithPath("description")
                                .type(JsonFieldType.STRING)
                                .description("Description of the unit"),
                        fieldWithPath("member_count")
                                .type(JsonFieldType.NUMBER)
                                .description("Count of currently active members with voting right for this unit."),
                        fieldWithPath("public_read")
                                .type(JsonFieldType.BOOLEAN)
                                .description("Are unit events readable by non-members"))))
                .andDo(document("units/get", pathParameters(
                        parameterWithName("unitId").description("The unit id"))));
    }

}
