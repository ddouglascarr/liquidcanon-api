-- NOTE: This file requires that sequence generators have not been used.
-- (All new rows need to start with id '1'.)

-- set transaction isolation level to be able to call "check_everything"() function


INSERT INTO "system_setting" ("member_ttl") VALUES ('31 days');

INSERT INTO "contingent" ("polling", "time_frame", "text_entry_limit", "initiative_limit") VALUES
  (FALSE, '60 minutes', 6, 10),
  (FALSE, '1 day', 60, 15),
  (FALSE, '1 week', 120, 20),
  (TRUE, '60 minutes', 6, 1),
  (TRUE, '1 day', 60, 10),
  (TRUE, '1 week', 120, 20);

-- So incompatable hashing algorythms can be used during dev
ALTER TABLE "member" ADD COLUMN "password_liquidcanon" VARCHAR(60);

INSERT INTO "member" ("id", "activated", "last_activity", "active", "login", "name") VALUES
  (1,  'now', 'now', TRUE, 'tender_hugle',  'Tender Hugle'),              -- id  1
  (2,  'now', 'now', TRUE, 'dreamy_almeida',  'Dreamy Almeida'),          -- id  2
  (3,  'now', 'now', TRUE, 'determined_poitras',  'Determined Poitras'),  -- id  3
  (4,  'now', 'now', TRUE, 'thirsty_swirles',  'Thirsty Swirles'),        -- id  4
  (5,  'now', 'now', TRUE, 'goofy_heisenberg',  'Goofy Heisenberg'),      -- id  5
  (6,  'now', 'now', TRUE, 'thirsty_babbage',  'Thirsty Babbage'),        -- id  6
  (7,  'now', 'now', TRUE, 'sick_lamarr',  'Sick Lamarr'),                -- id  7
  (8,  'now', 'now', TRUE, 'admiring_sammet',  'Admiring Sammet'),        -- id  8
  (9,  'now', 'now', TRUE, 'compassionate_bose',  'Compassionate Bose'),  -- id  9
  (10, 'now', 'now', TRUE, 'fervent_wright',  'Fervent Wright'),          -- id 10
  (11, 'now', 'now', TRUE, 'elated_meninsky',  'Elated Meninsky'),        -- id 11
  (12, 'now', 'now', TRUE, 'focused_bell',  'Focused Bell'),              -- id 12
  (13, 'now', 'now', TRUE, 'romantic_carson',  'Romantic Carson'),        -- id 13
  (14, 'now', 'now', TRUE, 'admiring_bartik',  'Admiring Bartik'),        -- id 14
  (15, 'now', 'now', TRUE, 'evil_austin',  'Evil Austin'),                -- id 15
  (16, 'now', 'now', TRUE, 'desperate_easley',  'Desperate Easley'),      -- id 16
  (17, 'now', 'now', TRUE, 'insane_poincare',  'Insane Poincare'),        -- id 17
  (18, 'now', 'now', TRUE, 'jovial_blackwell',  'Jovial Blackwell'),      -- id 18
  (19, 'now', 'now', TRUE, 'goofy_khorana',  'Goofy Khorana'),            -- id 19
  (20, 'now', 'now', TRUE, 'kickass_fermat',  'Kickass Fermat'),          -- id 20
  (21, 'now', 'now', TRUE, 'drunk_saha',  'Drunk Saha'),                  -- id 21
  (22, 'now', 'now', TRUE, 'angry_ritchie',  'Angry Ritchie'),            -- id 22
  (23, 'now', 'now', TRUE, 'mad_mcnulty',  'Mad Mcnulty'),                -- id 23
  (24, 'now', 'now', TRUE, 'admin', 'Admin #1' );                         -- id 24

-- set password to "login"
UPDATE "member" SET "password" = '$1$PcI6b1Bg$2SHjAZH2nMLFp0fxHis.Q0';
UPDATE "member" SET "password_liquidcanon" = '$2a$10$S14jaCA5zJ3lZ/r7eTQEae5NDV69isO20LCcOnojPf6MeR4hmexd2';
UPDATE "member" SET admin = TRUE WHERE login = 'admin';

