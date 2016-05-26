#!/bin/node
"use strict"
// Output to test-world.js to get test-world sql generation

// Variables generated from Integration Test Consts.
// cat ./src/test/java/org/ddouglascarr/utils/IntegrationTestConsts.java | \
//      sed 's/public static UUID/var/g' | \
//      sed 's/UUID.fromString(//g' | \
//      sed 's/)//g' | \
//      sed 's/public static String/var/g'

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
var MARS_STATUTES_AREA_ID = "d3fb43d8-d249-4c42-8f3e-628170bf7323";
var ALIEN_AFFAIRS_AREA_ID = "99ffb1d2-20d4-4898-b219-69d5a75619d1";
var MARS_MINERAL_RESOURCES_AREA_ID = "10b0465f-bd1c-4430-9879-e31cf827bfd5";
var EARTH_MOON_FEDERATION_STATUTES_AREA_ID = "7e9dbf52-feb1-4399-a664-634d6e7f70f2";
var EARTH_SPACE_VEHICLES_AREA_ID = "92da2047-51ee-4308-b72d-ce1ea800f631";

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
  ('${POITRAS_MEMBER_ID}',  'now', 'now', TRUE, ${POITRAS_LOGIN},  'Determined Poitras'),
  ('${HUGLE_MEMBER_ID}',  'now', 'now', TRUE, 'tender_hugle',  'Tender Hugle'),              -- id  2
  ('${ALMEIDA_MEMBER_ID}',  'now', 'now', TRUE, 'dreamy_almeida',  'Dreamy Almeida'),          -- id  3
  (4,  'now', 'now', TRUE, 'thirsty_swirles',  'Thirsty Swirles'),        -- id  4
  ('${HEISENBERG_MEMBER_ID}',  'now', 'now', TRUE, 'goofy_heisenberg',  'Goofy Heisenberg'),      -- id  5
  ('${BABBAGE_MEMBER_ID}',  'now', 'now', TRUE, 'thirsty_babbage',  'Thirsty Babbage'),        -- id  6
  (7,  'now', 'now', TRUE, 'sick_lamarr',  'Sick Lamarr'),                -- id  7
  (8,  'now', 'now', TRUE, 'admiring_sammet',  'Admiring Sammet'),        -- id  8
  (9,  'now', 'now', TRUE, 'compassionate_bose',  'Compassionate Bose'),  -- id  9
  (10, 'now', 'now', TRUE, 'fervent_wright',  'Fervent Wright'),          -- id 10
  (11, 'now', 'now', TRUE, 'focused_bell',  'Focused Bell'),              -- id 11
  (12, 'now', 'now', TRUE, 'elated_meninsky',  'Elated Meninsky'),        -- id 12
  ('${CARSON_MEMBER_ID}', 'now', 'now', TRUE, 'romantic_carson',  'Romantic Carson'),        -- id 13
  (14, 'now', 'now', TRUE, 'admiring_bartik',  'Admiring Bartik'),        -- id 14
  (15, 'now', 'now', TRUE, 'evil_austin',  'Evil Austin'),                -- id 15
  (16, 'now', 'now', TRUE, 'mad_mcnulty',  'Mad Mcnulty'),                -- id 16
  (17, 'now', 'now', TRUE, 'insane_poincare',  'Insane Poincare'),        -- id 17
  (18, 'now', 'now', TRUE, 'jovial_blackwell',  'Jovial Blackwell'),      -- id 18
  ('${KHORANA_MEMBER_ID}', 'now', 'now', TRUE, 'goofy_khorana',  'Goofy Khorana'),            -- id 19
  (20, 'now', 'now', TRUE, 'kickass_fermat',  'Kickass Fermat'),          -- id 20
  ('${SAHA_MEMBER_ID}', 'now', 'now', TRUE, 'drunk_saha',  'Drunk Saha'),                  -- id 21
  (22, 'now', 'now', TRUE, 'angry_ritchie',  'Angry Ritchie'),            -- id 22
  (23, 'now', 'now', TRUE, 'desperate_easley',  'Desperate Easley'),      -- id 23
  (24, 'now', 'now', TRUE, 'admin', 'Admin #1' );                         -- id 24

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


`;

console.log(sql);
