-- Database modifications necessary for liquid-canon
DROP TABLE IF EXISTS "unit_permissions";
CREATE TABLE "unit_permissions" (
        "id"            SERIAL4         PRIMARY KEY,
        "unit_id"       SERIAL4         UNIQUE,
        FOREIGN KEY ("unit_id") REFERENCES "unit" ("id"),
        "public_read"   BOOLEAN         NOT NULL);
COMMENT ON COLUMN "unit_permissions"."public_read" IS 'Set to TRUE if only members can read data from unit';

-- Add unit_id column
DROP VIEW area_delegation;
CREATE OR REPLACE VIEW area_delegation AS
 SELECT DISTINCT ON (area.id, delegation.truster_id) area.id AS area_id,
    area.unit_id,
    delegation.id,
    delegation.truster_id,
    delegation.trustee_id,
    delegation.scope
   FROM area
     JOIN delegation ON delegation.unit_id = area.unit_id OR delegation.area_id = area.id
     JOIN member ON delegation.truster_id = member.id
     JOIN privilege ON area.unit_id = privilege.unit_id AND delegation.truster_id = privilege.member_id
  WHERE member.active AND privilege.voting_right
  ORDER BY area.id, delegation.truster_id, delegation.scope DESC;

