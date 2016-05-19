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
                "classpath:sql/liquid-canon.sql",
                "classpath:sql/test-world.sql"
        })
public class MembersControllerTests
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
    public void getMemberShouldReturnMember() throws Exception
    {
        mockMvc.perform(get("/units/{unitId}/members/{memberId}",
                                EARTH_MOON_FEDERATION_UNIT_ID.intValue(),
                                HUGLE_MEMBER_ID.intValue())
                        .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(HUGLE_MEMBER_ID.intValue())))
                .andExpect(jsonPath("$.name", is("Tender Hugle")))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.last_activity", is(JsonFieldType.STRING)))
                .andExpect(jsonPath("$.login", is("tender_hugle")))
                .andDo(document("members/get", pathParameters(
                        parameterWithName("unitId")
                                .description("The unit to which the member belongs"),
                        parameterWithName("memberId")
                                .description("The id of the member"))))
                .andDo(document("members/get", responseFields(
                        fieldWithPath("id")
                                .type(JsonFieldType.NUMBER)
                                .description("Primary key"),
                        fieldWithPath("name")
                                .type(JsonFieldType.STRING)
                                .description("Distinct name of the member, may be changed freely"),
                        fieldWithPath("active")
                                .type(JsonFieldType.BOOLEAN)
                                .description("Memberships, support and votes are taken into account when corresponding members are marked as active. Automatically set to FALSE, if \"last_activity\" is older than \"member_ttl\""),
                        fieldWithPath("last_activity")
                                .type(JsonFieldType.NUMBER)
                                .description("Date of last activity of member"))));
    }
}
