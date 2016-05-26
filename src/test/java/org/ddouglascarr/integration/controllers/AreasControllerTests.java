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
import static org.ddouglascarr.utils.IntegrationTestConsts.*;
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
        }
)
public class AreasControllerTests
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
    public void getAreasShouldReturnListOfAreas() throws Exception
    {
        mockMvc.perform(get("/units/{unitId}/areas/{areaId}",
                        EARTH_MOON_FEDERATION_UNIT_ID.toString(),
                        EARTH_MOON_FEDERATION_STATUTES_AREA_ID.toString())
                    .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(EARTH_MOON_FEDERATION_STATUTES_AREA_ID.toString())))
                .andExpect(jsonPath("$.unit_id", is(EARTH_MOON_FEDERATION_UNIT_ID.toString())))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.name", is("Statutes of the Earth Moon Federation")))
                .andExpect(jsonPath("$.description", isEmptyOrNullString())) // TODO
                .andExpect(jsonPath("$.direct_member_count", isEmptyOrNullString())) // TODO
                .andExpect(jsonPath("$.member_weight", isEmptyOrNullString())) // TODO
                .andExpect(jsonPath("$.external_reference", isEmptyOrNullString())) // TODO
                .andExpect(jsonPath("$.*", hasSize(8)))
                .andDo(document("areas/get", pathParameters(
                        parameterWithName("unitId").description("The unit id to which the area belongs"),
                        parameterWithName("areaId").description("The area id"))))
                .andDo(document("areas/get", responseFields(
                        fieldWithPath("id")
                                .type(JsonFieldType.STRING)
                                .description("Primary key"),
                        fieldWithPath("unit_id")
                                .type(JsonFieldType.STRING)
                                .description("Unit to which the area belongs"),
                        fieldWithPath("active")
                                .type(JsonFieldType.BOOLEAN)
                                .description("New issues can be created in the area"),
                        fieldWithPath("name")
                                .type(JsonFieldType.STRING)
                                .description("Name of area"),
                        fieldWithPath("description")
                                .type(JsonFieldType.STRING)
                                .description("Description of area"),
                        fieldWithPath("direct_member_count")
                                .type(JsonFieldType.NUMBER)
                                .description("Count of active members of that area (ignoring their weight)"),
                        fieldWithPath("member_weight")
                                .type(JsonFieldType.NUMBER)
                                .description("Same as direct_member_count, but respecting delegations"),
                        fieldWithPath("external_reference")
                                .type(JsonFieldType.STRING)
                                .description("Reference to resource outside of project"))));
    }
}
