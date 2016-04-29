-- Database modifications necessary for liquid-canon
DROP TABLE IF EXISTS "unit_permissions";
CREATE TABLE "unit_permissions" (
        "id"            SERIAL4         PRIMARY KEY,
        "unit_id"       SERIAL4         UNIQUE,
        FOREIGN KEY ("unit_id") REFERENCES "unit" ("id"),
        "public_read"   BOOLEAN         NOT NULL);
COMMENT ON COLUMN "unit_permissions"."public_read" IS 'Set to TRUE if only members can read data from unit';

