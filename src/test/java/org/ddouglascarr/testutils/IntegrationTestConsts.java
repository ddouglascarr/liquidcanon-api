package org.ddouglascarr.testutils;

import java.util.UUID;

// bash UUID generator: echo -n UUID.fromString\(\"$(uuidgen)\"\)\; | xclip -selection clipboard
public class IntegrationTestConsts
{
    // member ids
    public static UUID NON_EXISTANT_MEMBER_ID = UUID.fromString("8068bcf3-31dd-41e9-b87f-e400828c352c");
    public static UUID POITRAS_MEMBER_ID = UUID.fromString("10ef659d-bea8-4c9c-a663-683e85392685");
    public static UUID HUGLE_MEMBER_ID = UUID.fromString("4c499b61-ba43-45bd-8640-ac5eedb969b7");
    public static UUID ALMEIDA_MEMBER_ID = UUID.fromString("f3cfebba-32ac-460d-bc7a-005d768aa03e");
    public static UUID HEISENBERG_MEMBER_ID = UUID.fromString("91a444b2-8b58-4c2a-95f4-6ec2ebd9e459");
    public static UUID BABBAGE_MEMBER_ID = UUID.fromString("c388fd86-bf47-4b80-ba2d-d6a9b404c301");
    public static UUID CARSON_MEMBER_ID = UUID.fromString("f8ba83a9-90cd-460e-b3c2-7f61f0ee6538");
    public static UUID KHORANA_MEMBER_ID = UUID.fromString("9025cdde-56c6-41ed-a87d-3c19ae0bd2e6");
    public static UUID SAHA_MEMBER_ID = UUID.fromString("0fcd54c0-abb1-4188-a592-0b583505a6f1");
    public static UUID ADMIN_MEMBER_ID = UUID.fromString("9e805039-7816-4270-a4f5-06b69a939ce5");

    // member credentials
    public static String POITRAS_LOGIN = "determined_poitras";
    public static String POITRAS_PASSWORD = "login";

    // unit ids
    public static UUID NON_EXISTENT_UNIT_ID = UUID.fromString("156501d1-e1a8-48c0-97c5-2832a4d66ba5");
    public static UUID SOLAR_SYSTEM_UNIT_ID = UUID.fromString("0987eb49-a650-417e-8b4e-816fcead2126");
    public static UUID EARTH_MOON_FEDERATION_UNIT_ID = UUID.fromString("5a57955c-949f-4953-b346-4c907e4de0d5");
    public static UUID EARTH_UNIT_ID = UUID.fromString("889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb");
    public static UUID MOON_UNIT_ID = UUID.fromString("6f5265f3-90f4-4b76-b051-1b51fa4eefdd");
    public static UUID MARS_UNIT_ID = UUID.fromString("6480dfc7-986b-410e-a6c2-f3ef4b711f5c");

    // unit names
    public static String EARTH_MOON_FEDERATION_UNIT_NAME = "Earth Moon Federation";

    // area ids
    public static UUID NON_EXISTANT_AREA_ID = UUID.fromString("33f53dbf-1442-4f7b-a900-12f586da7406");
    public static UUID STATUTES_OF_THE_UNITED_SOLAR_SYSTEM_AREA_ID = UUID.fromString("752f442d-58b7-4261-9d77-60c37b3d8378");
    public static UUID EARTH_MOON_FEDERATION_STATUTES_AREA_ID = UUID.fromString("7e9dbf52-feb1-4399-a664-634d6e7f70f2");
    public static UUID MARS_STATUTES_AREA_ID = UUID.fromString("d3fb43d8-d249-4c42-8f3e-628170bf7323");
    public static UUID INTRA_SOLAR_SPACE_TRAVEL_AREA_ID = UUID.fromString("7f4272ce-9996-441a-b75b-b15a01ce7345");
    public static UUID INTRA_SOLAR_SYSTEM_TRADE_AND_TAXATION_AREA_ID = UUID.fromString("005c3934-3c10-40c2-bf5e-49d2e6bd935a");
    public static UUID COMET_DEFENSE_AND_BLACK_HOLES_MANAGEMENT_AREA_ID = UUID.fromString("45184e5b-14bd-4e03-9b97-eb28c72d7748");
    public static UUID ALIEN_AFFAIRS_AREA_ID = UUID.fromString("99ffb1d2-20d4-4898-b219-69d5a75619d1");
    public static UUID EARTH_MOON_FEDERATION_FOREIGN_AFFAIRS_AREA_ID = UUID.fromString("3bcd2520-6fea-4b81-a6e8-225054172b27");
    public static UUID MOON_AFFAIRS_AREA_ID = UUID.fromString("b64d021e-decc-4206-b537-0d81d2c7ab55");
    public static UUID EARTH_AFFAIRS_AREA_ID = UUID.fromString("074292ca-0dbd-42ee-827e-501698e7f96c");
    public static UUID MOON_TOURISM_AREA_ID = UUID.fromString("b7dbf24d-cad1-4368-8122-cfa559bbb28e");
    public static UUID MARS_FOREIGN_AFFAIRS_AREA_ID = UUID.fromString("12777cda-31aa-4ca6-8c61-5838bcc888e0");
    public static UUID EARTH_SPACE_VEHICLES_AREA_ID = UUID.fromString("92da2047-51ee-4308-b72d-ce1ea800f631");
    public static UUID ENVIRONMENT_AREA_ID = UUID.fromString("17bf505c-1f1d-4d92-a4ef-10d3129a143e");
    public static UUID MOON_ENERGY_AND_OXYGEN_AREA_ID = UUID.fromString("d7d59392-95aa-4436-8cd8-cf0277ddc677");
    public static UUID MARS_ENERGY_AND_OXYGEN_AREA_ID = UUID.fromString("bb80ec37-3d03-4eea-a8c0-c51498bc42e4");
    public static UUID MARS_MINERAL_RESOURCES_AREA_ID = UUID.fromString("10b0465f-bd1c-4430-9879-e31cf827bfd5");

    // policy ids
    public static UUID SOLAR_SYSTEM_STATUTES_POLICY_ID = UUID.fromString("25ae80aa-f0b5-47f6-8ecf-3ac6817e78c6");
    public static UUID EARTH_MOON_FEDERATION_STATUTES_POLICY_ID = UUID.fromString("a7c72c67-fed9-4593-9b83-96c349aa1241");
    public static UUID MARS_STATUTES_POLICY_ID = UUID.fromString("e1c405c6-9efc-4d90-8fd7-d91cd2c35a3f");
    public static UUID PROPOSITION_POLICY_ID = UUID.fromString("88c0ffcb-f05a-40ef-ae5d-182f21b1c82e");
    public static UUID NON_BINDING_SURVEY_POLICY_ID = UUID.fromString("e94b1352-423d-416e-819b-f2d6011bb5dd");
    public static UUID FAST_NON_BINDING_SURVEY_POLICY_ID = UUID.fromString("bc600b10-496d-497a-ab51-2bae548a95ca");

}

