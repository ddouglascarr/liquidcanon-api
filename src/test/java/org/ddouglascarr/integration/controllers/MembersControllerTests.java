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

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public void getMemberShould401IfNoUnitReadPermission() throws Exception
    {
        mockMvc.perform(get("/units/{unitId}/members/{memberId}",
                                MARS_UNIT_ID.intValue(), SAHA_MEMBER_ID.intValue())
                            .with(httpBasic(POITRAS_LOGIN, POITRAS_PASSWORD)))
                .andExpect(status().isUnauthorized());
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
                .andExpect(jsonPath("$.activated", is(isA(Long.class))))
                .andExpect(jsonPath("$.identification", is("123456")))
                .andExpect(jsonPath("$.last_activity", isA(Long.class)))
                .andExpect(jsonPath("$.last_login", isA(Long.class)))
                .andExpect(jsonPath("$.locked", isOneOf(null, false)))
                .andExpect(jsonPath("$.admin", is(false)))
                .andExpect(jsonPath("$.organizational_unit", is("Silicon Valley")))
                .andExpect(jsonPath("$.internal_posts", is("Chief Scientist")))
                .andExpect(jsonPath("$.realname", is("Frances Hugle")))
                .andExpect(jsonPath("$.birthday", is("1927-08-13"))) //
                .andExpect(jsonPath("$.email", is("tender_hugle@internet.com")))
                .andExpect(jsonPath("$.xmpp_address", is("tender@tender_hugle.com")))
                .andExpect(jsonPath("$.website", is("https://www.tender-hugle.com")))
                .andExpect(jsonPath("$.phone", is("+61 5 5555 5555")))
                .andExpect(jsonPath("$.mobile_phone", is("+61 555 555 555")))
                .andExpect(jsonPath("$.profession", is("scientist, engineer, inventor")))
                .andExpect(jsonPath("$.external_memberships", is("Standard Electronics Research Corp.")))
                .andExpect(jsonPath("$.external_posts", is("Director of Research")))
                .andExpect(jsonPath("$.formatting_engine", is("plain")))
                .andExpect(jsonPath("$.statement", is("an American scientist, engineer, and inventor who contributed to the understanding of semiconductors, integrated circuitry, and the unique electrical principles of microscopic materials.")))
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
                        fieldWithPath("activated")
                                .type(JsonFieldType.NUMBER)
                                .description("(timestamp) First activation of account (i.e. usage of \"invite_code\")"),
                        fieldWithPath("last_activity")
                                .type(JsonFieldType.NUMBER).description("(timestamp) Date of last activity of member"),
                        fieldWithPath("last_login")
                                .type(JsonFieldType.NUMBER)
                                .description("(timestamp) Timestamp of last login in main interface"),
                        fieldWithPath("locked")
                                .type(JsonFieldType.BOOLEAN)
                                .description("Locked members can not log in"),
                        fieldWithPath("admin")
                                .type(JsonFieldType.BOOLEAN)
                                .description("Indicates if member is administrator of the system"),
                        fieldWithPath("identification")
                                .type(JsonFieldType.STRING)
                                .description("Optional identification number or code of the member"),
                        fieldWithPath("organizational_unit")
                                .type(JsonFieldType.STRING)
                                .description("Branch or division of the organization the member belongs to"),
                        fieldWithPath("internal_posts")
                                .type(JsonFieldType.STRING)
                                .description("Posts (offices) of the member inside the organization"),
                        fieldWithPath("realname")
                                .type(JsonFieldType.STRING)
                                .description("Real name of the member, may be identical with \"name\""),
                        fieldWithPath("birthday")
                                .type(JsonFieldType.STRING)
                                .description("(date) Birth date"),
                        fieldWithPath("email")
                                .type(JsonFieldType.STRING)
                                .description("(e-mail) Published email address of member; not used for system notifications"),
                        fieldWithPath("xmpp_address")
                                .type(JsonFieldType.STRING)
                                .description("(xmpp) instant messenging address"),
                        fieldWithPath("website")
                                .type(JsonFieldType.STRING)
                                .description("(url) Members personal website"),
                        fieldWithPath("phone")
                                .type(JsonFieldType.STRING)
                                .description("(phone-number) Members contact number"),
                        fieldWithPath("mobile_phone")
                                .type(JsonFieldType.STRING)
                                .description("(phone-number) Members mobile number"),
                        fieldWithPath("profession")
                                .type(JsonFieldType.STRING)
                                .description("members profession"),
                        fieldWithPath("external_memberships")
                                .type(JsonFieldType.STRING)
                                .description("Other organizations the member is involved in"),
                        fieldWithPath("external_posts")
                                .type(JsonFieldType.STRING)
                                .description("Posts (offices) outside the organization"),
                        fieldWithPath("formatting_engine")
                                .type(JsonFieldType.STRING)
                                .description("Formatting engine used for \"statement\""),
                        fieldWithPath("statement")
                                .type(JsonFieldType.STRING)
                                .description("Freely chosen text of the member for their profile"))));
    }
}
