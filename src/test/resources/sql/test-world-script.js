#!/bin/node
"use strict"
// Output to test-world.js to get test-world sql generation

// Variables generated from Integration Test Consts.
// cat ./src/test/java/org/ddouglascarr/utils/IntegrationTestConsts.java | \
//      sed 's/public static UUID/var/g' | \
//      sed 's/UUID.fromString(//g' | \
//      sed 's/)//g' | \
//      sed 's/public static String/var/g'

// credit Jon  Surrell @ http://stackoverflow.com/questions/105034/create-guid-uuid-in-javascript
function uuid() {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
    s4() + '-' + s4() + s4() + s4();
}

// member ids
var NON_EXISTANT_MEMBER_ID = "8068bcf3-31dd-41e9-b87f-e400828c352c";
var POITRAS_MEMBER_ID = "10ef659d-bea8-4c9c-a663-683e85392685";
var HUGLE_MEMBER_ID = "4c499b61-ba43-45bd-8640-ac5eedb969b7";
var ALMEIDA_MEMBER_ID = "f3cfebba-32ac-460d-bc7a-005d768aa03e";
var HEISENBERG_MEMBER_ID = "91a444b2-8b58-4c2a-95f4-6ec2ebd9e459";
var BABBAGE_MEMBER_ID = "c388fd86-bf47-4b80-ba2d-d6a9b404c301";
var CARSON_MEMBER_ID = "f8ba83a9-90cd-460e-b3c2-7f61f0ee6538";
var KHORANA_MEMBER_ID = "9025cdde-56c6-41ed-a87d-3c19ae0bd2e6";
var SAHA_MEMBER_ID = "0fcd54c0-abb1-4188-a592-0b583505a6f1";

// member credentials
var POITRAS_LOGIN = "determined_poitras";
var POITRAS_PASSWORD = "login";

// unit ids
var NON_EXISTENT_UNIT_ID = "156501d1-e1a8-48c0-97c5-2832a4d66ba5";
var SOLAR_SYSTEM_UNIT_ID = "0987eb49-a650-417e-8b4e-816fcead2126";
var EARTH_MOON_FEDERATION_UNIT_ID = "5a57955c-949f-4953-b346-4c907e4de0d5";
var EARTH_UNIT_ID = "889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb";
var MOON_UNIT_ID = "6f5265f3-90f4-4b76-b051-1b51fa4eefdd";
var MARS_UNIT_ID = "6480dfc7-986b-410e-a6c2-f3ef4b711f5c";

// unit names
var EARTH_MOON_FEDERATION_UNIT_NAME = "Earth Moon Federation";

// area ids
var NON_EXISTANT_AREA_ID = "33f53dbf-1442-4f7b-a900-12f586da7406";
var STATUTES_OF_THE_UNITED_SOLAR_SYSTEM_AREA_ID = "752f442d-58b7-4261-9d77-60c37b3d8378";
var EARTH_MOON_FEDERATION_STATUTES_AREA_ID = "7e9dbf52-feb1-4399-a664-634d6e7f70f2";
var MARS_STATUTES_AREA_ID = "d3fb43d8-d249-4c42-8f3e-628170bf7323";
var INTRA_SOLAR_SPACE_TRAVEL_AREA_ID = "7f4272ce-9996-441a-b75b-b15a01ce7345";
var INTRA_SOLAR_SYSTEM_TRADE_AND_TAXATION_AREA_ID = "005c3934-3c10-40c2-bf5e-49d2e6bd935a";
var COMET_DEFENSE_AND_BLACK_HOLES_MANAGEMENT_AREA_ID = "45184e5b-14bd-4e03-9b97-eb28c72d7748";
var ALIEN_AFFAIRS_AREA_ID = "99ffb1d2-20d4-4898-b219-69d5a75619d1";
var EARTH_MOON_FEDERATION_FOREIGN_AFFAIRS_AREA_ID = "3bcd2520-6fea-4b81-a6e8-225054172b27";
var MOON_AFFAIRS_AREA_ID = "b64d021e-decc-4206-b537-0d81d2c7ab55";
var EARTH_AFFAIRS_AREA_ID = "074292ca-0dbd-42ee-827e-501698e7f96c";
var MOON_TOURISM_AREA_ID = "b7dbf24d-cad1-4368-8122-cfa559bbb28e";
var MARS_FOREIGN_AFFAIRS_AREA_ID = "12777cda-31aa-4ca6-8c61-5838bcc888e0";
var EARTH_SPACE_VEHICLES_AREA_ID = "92da2047-51ee-4308-b72d-ce1ea800f631";
var ENVIRONMENT_AREA_ID = "17bf505c-1f1d-4d92-a4ef-10d3129a143e";
var MOON_ENERGY_AND_OXYGEN_AREA_ID = "d7d59392-95aa-4436-8cd8-cf0277ddc677";
var MARS_ENERGY_AND_OXYGEN_AREA_ID = "bb80ec37-3d03-4eea-a8c0-c51498bc42e4";
var MARS_MINERAL_RESOURCES_AREA_ID = "10b0465f-bd1c-4430-9879-e31cf827bfd5";

// policy ids
var SOLAR_SYSTEM_STATUTES_POLICY_ID = "25ae80aa-f0b5-47f6-8ecf-3ac6817e78c6";
var EARTH_MOON_FEDERATION_STATUTES_POLICY_ID = "a7c72c67-fed9-4593-9b83-96c349aa1241";
var MARS_STATUTES_POLICY_ID = "e1c405c6-9efc-4d90-8fd7-d91cd2c35a3f";
var PROPOSITION_POLICY_ID = "88c0ffcb-f05a-40ef-ae5d-182f21b1c82e";
var NON_BINDING_SURVEY_POLICY_ID = "e94b1352-423d-416e-819b-f2d6011bb5dd";
var FAST_NON_BINDING_SURVEY_POLICY_ID = "bc600b10-496d-497a-ab51-2bae548a95ca";


var sql = `
INSERT INTO "system_setting" ("member_ttl") VALUES ('31 days');

INSERT INTO "contingent" ("polling", "time_frame", "text_entry_limit", "initiative_limit") VALUES
  (FALSE, '60 minutes', 6, 10),
  (FALSE, '1 day', 60, 15),
  (FALSE, '1 week', 120, 20),
  (TRUE, '60 minutes', 6, 1),
  (TRUE, '1 day', 60, 10),
  (TRUE, '1 week', 120, 20);

INSERT INTO "member" ("id", "activated", "last_activity", "active", "login", "name") VALUES
  ('${POITRAS_MEMBER_ID}',  'now', 'now', TRUE, '${POITRAS_LOGIN}',  'Determined Poitras'),
  ('${HUGLE_MEMBER_ID}',  'now', 'now', TRUE, 'tender_hugle',  'Tender Hugle'),              -- id  2
  ('${ALMEIDA_MEMBER_ID}',  'now', 'now', TRUE, 'dreamy_almeida',  'Dreamy Almeida'),          -- id  3
  ('${HEISENBERG_MEMBER_ID}',  'now', 'now', TRUE, 'goofy_heisenberg',  'Goofy Heisenberg'),      -- id  5
  ('${BABBAGE_MEMBER_ID}',  'now', 'now', TRUE, 'thirsty_babbage',  'Thirsty Babbage'),        -- id  6
  ('${CARSON_MEMBER_ID}', 'now', 'now', TRUE, 'romantic_carson',  'Romantic Carson'),        -- id 13
  ('${KHORANA_MEMBER_ID}', 'now', 'now', TRUE, 'goofy_khorana',  'Goofy Khorana'),            -- id 19
  ('${SAHA_MEMBER_ID}', 'now', 'now', TRUE, 'drunk_saha',  'Drunk Saha');                  -- id 21

-- set password to "login"
UPDATE "member" SET "password" = '$1$PcI6b1Bg$2SHjAZH2nMLFp0fxHis.Q0';
UPDATE "member" SET "password_liquidcanon" = '$2a$10$S14jaCA5zJ3lZ/r7eTQEae5NDV69isO20LCcOnojPf6MeR4hmexd2';
UPDATE "member" SET admin = TRUE WHERE login = 'admin';

-- extra details for integration test
UPDATE "member" SET "locked" = FALSE, "last_login" = 'now', "organizational_unit" = 'Silicon Valley',
    "internal_posts" = 'Chief Scientist', "realname" = 'Frances Hugle', "birthday" = '1927-08-13T00:00:00',
     "identification" = '123456', "email" = 'tender_hugle@internet.com', "xmpp_address" = 'tender@tender_hugle.com',
     "website" = 'https://www.tender-hugle.com', "phone" = '+61 5 5555 5555',
     "mobile_phone" = '+61 555 555 555', "profession" = 'scientist, engineer, inventor',
     "external_memberships" = 'Standard Electronics Research Corp.',
     "external_posts" = 'Director of Research', "formatting_engine" = 'plain',
     "statement" = 'an American scientist, engineer, and inventor who contributed to the understanding of semiconductors, integrated circuitry, and the unique electrical principles of microscopic materials.'
     WHERE "login" = 'tender_hugle';

INSERT INTO "policy" (
    "id",
    "index",
    "name",
    "min_admission_time", "max_admission_time",
    "discussion_time",
    "verification_time",
    "voting_time",
    "issue_quorum_num", "issue_quorum_den",
    "initiative_quorum_num", "initiative_quorum_den",
    "direct_majority_num", "direct_majority_den", "direct_majority_strict",
    "indirect_majority_num", "indirect_majority_den", "indirect_majority_strict",
    "no_reverse_beat_path", "no_multistage_majority"
  ) VALUES (
    '${SOLAR_SYSTEM_STATUTES_POLICY_ID}',
    1,
    'amendment of the statutes (solar system)',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    2, 3, FALSE,
    TRUE, FALSE
  ), (
    '${EARTH_MOON_FEDERATION_STATUTES_POLICY_ID}',
    2,
    'amendment of the statutes (earth moon federation)',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    2, 3, FALSE,
    TRUE, FALSE
  ), (
    '${MARS_STATUTES_POLICY_ID}',
    3,
    'amendment of the statutes (united mars colonies)',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    2, 3, FALSE,
    TRUE, FALSE
  ), (
    '${PROPOSITION_POLICY_ID}',
    4,
    'proposition',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    1, 2, TRUE,
    TRUE, FALSE
  ), (
    '${NON_BINDING_SURVEY_POLICY_ID}',
    5,
    'non-binding survey',
    '0', '2 days', '3 days', '2 days', '3 days',
    5, 100,
    5, 100,
    1, 2, TRUE,
    1, 2, TRUE,
    TRUE, FALSE
  ), (
    '${FAST_NON_BINDING_SURVEY_POLICY_ID}',
    6,
    'non-binding survey (super fast)',
    '0', '1 hour', '30 minutes', '15 minutes', '30 minutes',
    5, 100,
    5, 100,
    1, 2, TRUE,
    1, 2, TRUE,
    TRUE, FALSE
  );


INSERT INTO "unit" ("id", "parent_id", "name", "public_read") VALUES
  ( '${SOLAR_SYSTEM_UNIT_ID}',           NULL,                      'Solar System',           TRUE),           -- id 1
  ( '${EARTH_MOON_FEDERATION_UNIT_ID}', '${SOLAR_SYSTEM_UNIT_ID}',  'Earth Moon Federation',  TRUE),  -- id 2
  ( '${EARTH_UNIT_ID}',                  '${SOLAR_SYSTEM_UNIT_ID}', 'Earth',                  TRUE),                  -- id 3
  ( '${MOON_UNIT_ID}',                   '${SOLAR_SYSTEM_UNIT_ID}', 'Moon',                   TRUE),                   -- id 4
  ( '${MARS_UNIT_ID}',                   '${SOLAR_SYSTEM_UNIT_ID}', 'Mars',                   FALSE);                   -- id 5

INSERT INTO "area" ("id", "unit_id", "name") VALUES
  ( '${STATUTES_OF_THE_UNITED_SOLAR_SYSTEM_AREA_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'Statutes of the United Solar System'),       -- id  1
  ( '${EARTH_MOON_FEDERATION_STATUTES_AREA_ID}',       '${EARTH_MOON_FEDERATION_UNIT_ID}', 'Statutes of the Earth Moon Federation'),     -- id  2
  ( '${MARS_STATUTES_AREA_ID}',                         '${MARS_UNIT_ID}', 'Statutes of the United Mars Colonies'),      -- id  3
  ( '${INTRA_SOLAR_SPACE_TRAVEL_AREA_ID}',              '${SOLAR_SYSTEM_UNIT_ID}', 'Intra solar space travel'),                  -- id  4
  ( '${INTRA_SOLAR_SYSTEM_TRADE_AND_TAXATION_AREA_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'Intra solar system trade and taxation'),     -- id  5
  ( '${COMET_DEFENSE_AND_BLACK_HOLES_MANAGEMENT_AREA_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'Comet defense and black holes management'),  -- id  6
  ( '${ALIEN_AFFAIRS_AREA_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'Alien affairs'),                             -- id  7
  ( '${EARTH_MOON_FEDERATION_FOREIGN_AFFAIRS_AREA_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', 'Foreign affairs'),                           -- id  8
  ( '${MOON_AFFAIRS_AREA_ID}', '${EARTH_UNIT_ID}', 'Moon affairs'),                              -- id  9
  ( '${EARTH_AFFAIRS_AREA_ID}', '${MOON_UNIT_ID}', 'Earth affairs'),                             -- id 10
  ( '${MOON_TOURISM_AREA_ID}', '${MOON_UNIT_ID}', 'Moon tourism'),                              -- id 11
  ( '${MARS_FOREIGN_AFFAIRS_AREA_ID}', '${MARS_UNIT_ID}', 'Foreign affairs'),                           -- id 12
  ( '${EARTH_SPACE_VEHICLES_AREA_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', 'Department of space vehicles'),              -- id 13
  ( '${ENVIRONMENT_AREA_ID}', '${EARTH_UNIT_ID}', 'Environment'),                               -- id 14
  ( '${MOON_ENERGY_AND_OXYGEN_AREA_ID}', '${MOON_UNIT_ID}', 'Energy and oxygen'),                         -- id 15
  ( '${MARS_ENERGY_AND_OXYGEN_AREA_ID}', '${MARS_UNIT_ID}', 'Energy and oxygen'),                         -- id 16
  ( '${MARS_MINERAL_RESOURCES_AREA_ID}', '${MARS_UNIT_ID}', 'Mineral resources');                         -- id 17


-- All are able to vote in Solar System
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '${POITRAS_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${HUGLE_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${ALMEIDA_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${HEISENBERG_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${BABBAGE_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${CARSON_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${KHORANA_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE ),
    ( '${SAHA_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', TRUE );

-- Earth Moon Federation privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '${POITRAS_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', TRUE ),
    ( '${HUGLE_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', TRUE ),
    ( '${ALMEIDA_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', TRUE ),
    ( '${HEISENBERG_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', TRUE ),
    ( '${BABBAGE_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', TRUE ),
    ( '${CARSON_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', TRUE );

-- Earth privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '${POITRAS_MEMBER_ID}', '${EARTH_UNIT_ID}', TRUE ),
    ( '${HUGLE_MEMBER_ID}', '${EARTH_UNIT_ID}', TRUE ),
    ( '${ALMEIDA_MEMBER_ID}', '${EARTH_UNIT_ID}', TRUE ),
    ( '${HEISENBERG_MEMBER_ID}', '${EARTH_UNIT_ID}', TRUE ),
    ( '${BABBAGE_MEMBER_ID}', '${EARTH_UNIT_ID}', TRUE );

-- Moon Privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '${CARSON_MEMBER_ID}', '${MOON_UNIT_ID}', TRUE );

-- Mars Privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '${KHORANA_MEMBER_ID}', '${MARS_UNIT_ID}', TRUE ),
    ( '${SAHA_MEMBER_ID}', '${MARS_UNIT_ID}', TRUE );

-- Unit Delegations
INSERT INTO "delegation"
    ( "id", "truster_id", "unit_id", "scope", "trustee_id" ) VALUES
    ( '${uuid()}', '${HUGLE_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'unit', '${POITRAS_MEMBER_ID}'),
    ( '${uuid()}', '${ALMEIDA_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'unit', '${POITRAS_MEMBER_ID}' ),
    ( '${uuid()}', '${HEISENBERG_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'unit', '${POITRAS_MEMBER_ID}' ),
    ( '${uuid()}', '${BABBAGE_MEMBER_ID}', '${SOLAR_SYSTEM_UNIT_ID}', 'unit', '${HUGLE_MEMBER_ID}' ),
    ( '${uuid()}', '${POITRAS_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', 'unit', '${HUGLE_MEMBER_ID}' ),
    ( '${uuid()}', '${ALMEIDA_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', 'unit', '${HUGLE_MEMBER_ID}' ),
    ( '${uuid()}', '${HEISENBERG_MEMBER_ID}', '${EARTH_MOON_FEDERATION_UNIT_ID}', 'unit', '${POITRAS_MEMBER_ID}' );

-- Area Delegations
INSERT INTO "delegation"
    ( "id", "truster_id", "area_id", "scope", "trustee_id" ) VALUES
    ( '${uuid()}', '${POITRAS_MEMBER_ID}', '${ALIEN_AFFAIRS_AREA_ID}', 'area', '${HUGLE_MEMBER_ID}' ),
    ( '${uuid()}', '${KHORANA_MEMBER_ID}', '${ALIEN_AFFAIRS_AREA_ID}', 'area', '${HEISENBERG_MEMBER_ID}' ),
    ( '${uuid()}', '${ALMEIDA_MEMBER_ID}', '${COMET_DEFENSE_AND_BLACK_HOLES_MANAGEMENT_AREA_ID}', 'area', '${HUGLE_MEMBER_ID}' ),
    ( '${uuid()}', '${ALMEIDA_MEMBER_ID}', '${ALIEN_AFFAIRS_AREA_ID}', 'area', '${HEISENBERG_MEMBER_ID}');

`;

console.log(sql);
