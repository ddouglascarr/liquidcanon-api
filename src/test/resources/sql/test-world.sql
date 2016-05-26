
INSERT INTO "system_setting" ("member_ttl") VALUES ('31 days');

INSERT INTO "contingent" ("polling", "time_frame", "text_entry_limit", "initiative_limit") VALUES
  (FALSE, '60 minutes', 6, 10),
  (FALSE, '1 day', 60, 15),
  (FALSE, '1 week', 120, 20),
  (TRUE, '60 minutes', 6, 1),
  (TRUE, '1 day', 60, 10),
  (TRUE, '1 week', 120, 20);

INSERT INTO "member" ("id", "activated", "last_activity", "active", "login", "name") VALUES
  ('10ef659d-bea8-4c9c-a663-683e85392685',  'now', 'now', TRUE, 'determined_poitras',  'Determined Poitras'),
  ('4c499b61-ba43-45bd-8640-ac5eedb969b7',  'now', 'now', TRUE, 'tender_hugle',  'Tender Hugle'),              -- id  2
  ('f3cfebba-32ac-460d-bc7a-005d768aa03e',  'now', 'now', TRUE, 'dreamy_almeida',  'Dreamy Almeida'),          -- id  3
  ('91a444b2-8b58-4c2a-95f4-6ec2ebd9e459',  'now', 'now', TRUE, 'goofy_heisenberg',  'Goofy Heisenberg'),      -- id  5
  ('c388fd86-bf47-4b80-ba2d-d6a9b404c301',  'now', 'now', TRUE, 'thirsty_babbage',  'Thirsty Babbage'),        -- id  6
  ('f8ba83a9-90cd-460e-b3c2-7f61f0ee6538', 'now', 'now', TRUE, 'romantic_carson',  'Romantic Carson'),        -- id 13
  ('9025cdde-56c6-41ed-a87d-3c19ae0bd2e6', 'now', 'now', TRUE, 'goofy_khorana',  'Goofy Khorana'),            -- id 19
  ('0fcd54c0-abb1-4188-a592-0b583505a6f1', 'now', 'now', TRUE, 'drunk_saha',  'Drunk Saha');                  -- id 21

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
    '25ae80aa-f0b5-47f6-8ecf-3ac6817e78c6',
    1,
    'amendment of the statutes (solar system)',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    2, 3, FALSE,
    TRUE, FALSE
  ), (
    'a7c72c67-fed9-4593-9b83-96c349aa1241',
    2,
    'amendment of the statutes (earth moon federation)',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    2, 3, FALSE,
    TRUE, FALSE
  ), (
    'e1c405c6-9efc-4d90-8fd7-d91cd2c35a3f',
    3,
    'amendment of the statutes (united mars colonies)',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    2, 3, FALSE,
    TRUE, FALSE
  ), (
    '88c0ffcb-f05a-40ef-ae5d-182f21b1c82e',
    4,
    'proposition',
    '0', '8 days', '15 days', '8 days', '15 days',
    10, 100,
    10, 100,
    1, 2, TRUE,
    1, 2, TRUE,
    TRUE, FALSE
  ), (
    'e94b1352-423d-416e-819b-f2d6011bb5dd',
    5,
    'non-binding survey',
    '0', '2 days', '3 days', '2 days', '3 days',
    5, 100,
    5, 100,
    1, 2, TRUE,
    1, 2, TRUE,
    TRUE, FALSE
  ), (
    'bc600b10-496d-497a-ab51-2bae548a95ca',
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
  ( '0987eb49-a650-417e-8b4e-816fcead2126',           NULL,                      'Solar System',           TRUE),           -- id 1
  ( '5a57955c-949f-4953-b346-4c907e4de0d5', '0987eb49-a650-417e-8b4e-816fcead2126',  'Earth Moon Federation',  TRUE),  -- id 2
  ( '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb',                  '0987eb49-a650-417e-8b4e-816fcead2126', 'Earth',                  TRUE),                  -- id 3
  ( '6f5265f3-90f4-4b76-b051-1b51fa4eefdd',                   '0987eb49-a650-417e-8b4e-816fcead2126', 'Moon',                   TRUE),                   -- id 4
  ( '6480dfc7-986b-410e-a6c2-f3ef4b711f5c',                   '0987eb49-a650-417e-8b4e-816fcead2126', 'Mars',                   FALSE);                   -- id 5

INSERT INTO "area" ("id", "unit_id", "name") VALUES
  ( '752f442d-58b7-4261-9d77-60c37b3d8378', '0987eb49-a650-417e-8b4e-816fcead2126', 'Statutes of the United Solar System'),       -- id  1
  ( '7e9dbf52-feb1-4399-a664-634d6e7f70f2',       '5a57955c-949f-4953-b346-4c907e4de0d5', 'Statutes of the Earth Moon Federation'),     -- id  2
  ( 'd3fb43d8-d249-4c42-8f3e-628170bf7323',                         '6480dfc7-986b-410e-a6c2-f3ef4b711f5c', 'Statutes of the United Mars Colonies'),      -- id  3
  ( '7f4272ce-9996-441a-b75b-b15a01ce7345',              '0987eb49-a650-417e-8b4e-816fcead2126', 'Intra solar space travel'),                  -- id  4
  ( '005c3934-3c10-40c2-bf5e-49d2e6bd935a', '0987eb49-a650-417e-8b4e-816fcead2126', 'Intra solar system trade and taxation'),     -- id  5
  ( '45184e5b-14bd-4e03-9b97-eb28c72d7748', '0987eb49-a650-417e-8b4e-816fcead2126', 'Comet defense and black holes management'),  -- id  6
  ( '99ffb1d2-20d4-4898-b219-69d5a75619d1', '0987eb49-a650-417e-8b4e-816fcead2126', 'Alien affairs'),                             -- id  7
  ( '3bcd2520-6fea-4b81-a6e8-225054172b27', '5a57955c-949f-4953-b346-4c907e4de0d5', 'Foreign affairs'),                           -- id  8
  ( 'b64d021e-decc-4206-b537-0d81d2c7ab55', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', 'Moon affairs'),                              -- id  9
  ( '074292ca-0dbd-42ee-827e-501698e7f96c', '6f5265f3-90f4-4b76-b051-1b51fa4eefdd', 'Earth affairs'),                             -- id 10
  ( 'b7dbf24d-cad1-4368-8122-cfa559bbb28e', '6f5265f3-90f4-4b76-b051-1b51fa4eefdd', 'Moon tourism'),                              -- id 11
  ( '12777cda-31aa-4ca6-8c61-5838bcc888e0', '6480dfc7-986b-410e-a6c2-f3ef4b711f5c', 'Foreign affairs'),                           -- id 12
  ( '92da2047-51ee-4308-b72d-ce1ea800f631', '5a57955c-949f-4953-b346-4c907e4de0d5', 'Department of space vehicles'),              -- id 13
  ( '17bf505c-1f1d-4d92-a4ef-10d3129a143e', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', 'Environment'),                               -- id 14
  ( 'd7d59392-95aa-4436-8cd8-cf0277ddc677', '6f5265f3-90f4-4b76-b051-1b51fa4eefdd', 'Energy and oxygen'),                         -- id 15
  ( 'bb80ec37-3d03-4eea-a8c0-c51498bc42e4', '6480dfc7-986b-410e-a6c2-f3ef4b711f5c', 'Energy and oxygen'),                         -- id 16
  ( '10b0465f-bd1c-4430-9879-e31cf827bfd5', '6480dfc7-986b-410e-a6c2-f3ef4b711f5c', 'Mineral resources');                         -- id 17


-- All are able to vote in Solar System
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '10ef659d-bea8-4c9c-a663-683e85392685', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( '4c499b61-ba43-45bd-8640-ac5eedb969b7', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( 'f3cfebba-32ac-460d-bc7a-005d768aa03e', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( '91a444b2-8b58-4c2a-95f4-6ec2ebd9e459', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( 'c388fd86-bf47-4b80-ba2d-d6a9b404c301', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( 'f8ba83a9-90cd-460e-b3c2-7f61f0ee6538', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( '9025cdde-56c6-41ed-a87d-3c19ae0bd2e6', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE ),
    ( '0fcd54c0-abb1-4188-a592-0b583505a6f1', '0987eb49-a650-417e-8b4e-816fcead2126', TRUE );

-- Earth Moon Federation privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '10ef659d-bea8-4c9c-a663-683e85392685', '5a57955c-949f-4953-b346-4c907e4de0d5', TRUE ),
    ( '4c499b61-ba43-45bd-8640-ac5eedb969b7', '5a57955c-949f-4953-b346-4c907e4de0d5', TRUE ),
    ( 'f3cfebba-32ac-460d-bc7a-005d768aa03e', '5a57955c-949f-4953-b346-4c907e4de0d5', TRUE ),
    ( '91a444b2-8b58-4c2a-95f4-6ec2ebd9e459', '5a57955c-949f-4953-b346-4c907e4de0d5', TRUE ),
    ( 'c388fd86-bf47-4b80-ba2d-d6a9b404c301', '5a57955c-949f-4953-b346-4c907e4de0d5', TRUE ),
    ( 'f8ba83a9-90cd-460e-b3c2-7f61f0ee6538', '5a57955c-949f-4953-b346-4c907e4de0d5', TRUE );

-- Earth privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '10ef659d-bea8-4c9c-a663-683e85392685', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', TRUE ),
    ( '4c499b61-ba43-45bd-8640-ac5eedb969b7', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', TRUE ),
    ( 'f3cfebba-32ac-460d-bc7a-005d768aa03e', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', TRUE ),
    ( '91a444b2-8b58-4c2a-95f4-6ec2ebd9e459', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', TRUE ),
    ( 'c388fd86-bf47-4b80-ba2d-d6a9b404c301', '889d2ba8-ca5f-4d42-ad04-7fc1d1ece1cb', TRUE );

-- Moon Privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( 'f8ba83a9-90cd-460e-b3c2-7f61f0ee6538', '6f5265f3-90f4-4b76-b051-1b51fa4eefdd', TRUE );

-- Mars Privileges
INSERT INTO "privilege" ("member_id", "unit_id", "voting_right") VALUES
    ( '9025cdde-56c6-41ed-a87d-3c19ae0bd2e6', '6480dfc7-986b-410e-a6c2-f3ef4b711f5c', TRUE ),
    ( '0fcd54c0-abb1-4188-a592-0b583505a6f1', '6480dfc7-986b-410e-a6c2-f3ef4b711f5c', TRUE );

-- Unit Delegations
INSERT INTO "delegation"
    ( "id", "truster_id", "unit_id", "scope", "trustee_id" ) VALUES
    ( '3bdbbaa5-615d-774f-f8bb-232c6956d0a1', '4c499b61-ba43-45bd-8640-ac5eedb969b7', '0987eb49-a650-417e-8b4e-816fcead2126', 'unit', '10ef659d-bea8-4c9c-a663-683e85392685'),
    ( 'c8282a95-4117-4d99-3fa6-d2ade5d80cc5', 'f3cfebba-32ac-460d-bc7a-005d768aa03e', '0987eb49-a650-417e-8b4e-816fcead2126', 'unit', '10ef659d-bea8-4c9c-a663-683e85392685' ),
    ( '99e2ee9f-2ca7-1b68-42f9-e48874dc9d67', '91a444b2-8b58-4c2a-95f4-6ec2ebd9e459', '0987eb49-a650-417e-8b4e-816fcead2126', 'unit', '10ef659d-bea8-4c9c-a663-683e85392685' ),
    ( 'ec682ffd-0dc0-75b4-cf20-e0218d291b47', 'c388fd86-bf47-4b80-ba2d-d6a9b404c301', '0987eb49-a650-417e-8b4e-816fcead2126', 'unit', '4c499b61-ba43-45bd-8640-ac5eedb969b7' ),
    ( '49589b51-f6fb-ec94-638a-c2626d7eb59e', '10ef659d-bea8-4c9c-a663-683e85392685', '5a57955c-949f-4953-b346-4c907e4de0d5', 'unit', '4c499b61-ba43-45bd-8640-ac5eedb969b7' ),
    ( '5c038d41-913e-8380-a8f5-bb732be5adf8', 'f3cfebba-32ac-460d-bc7a-005d768aa03e', '5a57955c-949f-4953-b346-4c907e4de0d5', 'unit', '4c499b61-ba43-45bd-8640-ac5eedb969b7' ),
    ( '9ca30fe6-e3ec-7bc7-d836-cd4133690596', '91a444b2-8b58-4c2a-95f4-6ec2ebd9e459', '5a57955c-949f-4953-b346-4c907e4de0d5', 'unit', '10ef659d-bea8-4c9c-a663-683e85392685' );

-- Area Delegations
INSERT INTO "delegation"
    ( "id", "truster_id", "area_id", "scope", "trustee_id" ) VALUES
    ( '7899a947-47af-d5c4-85f0-67ef278b1c52', '10ef659d-bea8-4c9c-a663-683e85392685', '99ffb1d2-20d4-4898-b219-69d5a75619d1', 'area', '4c499b61-ba43-45bd-8640-ac5eedb969b7' ),
    ( 'a21d9e40-ac19-f447-8fda-aee08bd3f6c6', '9025cdde-56c6-41ed-a87d-3c19ae0bd2e6', '99ffb1d2-20d4-4898-b219-69d5a75619d1', 'area', '91a444b2-8b58-4c2a-95f4-6ec2ebd9e459' ),
    ( '538b2b9e-08e0-1c40-a5f1-3b535d514f20', 'f3cfebba-32ac-460d-bc7a-005d768aa03e', '45184e5b-14bd-4e03-9b97-eb28c72d7748', 'area', '4c499b61-ba43-45bd-8640-ac5eedb969b7' );


