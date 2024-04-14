DROP SEQUENCE IF EXISTS "public"."Player_idPlayer_seq";
CREATE SEQUENCE "public"."Player_idPlayer_seq"
    INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

CREATE TABLE "public"."Player"
(
    "idPlayer" int4 NOT NULL DEFAULT nextval('"Player_idPlayer_seq"'::regclass),
    "name"   varchar(50),
    "birthday"  date,
    "team"  int4,
    PRIMARY KEY ("idPlayer")
);


DROP SEQUENCE IF EXISTS "public"."Team_idTeam_seq";
CREATE SEQUENCE "public"."Team_idTeam_seq"
    INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

CREATE TABLE "public"."Team"
(
    "idTeam" int4 NOT NULL DEFAULT nextval('"Team_idTeam_seq"'::regclass),
    "name"   varchar(100),
    "city"  varchar(100),
    PRIMARY KEY ("idTeam")
);

ALTER TABLE "public"."Player" ADD CONSTRAINT "FK_PLAYER_TEAM" FOREIGN KEY ("team") REFERENCES "public"."Team" ("idTeam") ON DELETE NO ACTION ON UPDATE NO ACTION;


DROP SEQUENCE IF EXISTS "public"."Match_idMatch_seq";
CREATE SEQUENCE "public"."Match_idMatch_seq"
    INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

CREATE TABLE "public"."Match"
(
    "idMatch" int4 NOT NULL DEFAULT nextval('"Match_idMatch_seq"'::regclass),
    "homeTeam" int4,
    "awayTeam" int4,
    "homeGoals" int4 ,
    "awayGoals" int4,
    "matchDate"  date,
    PRIMARY KEY ("idMatch")
);

ALTER TABLE "public"."Match" ADD CONSTRAINT "FK_MATCH_HOME_TEAM" FOREIGN KEY ("homeTeam") REFERENCES "public"."Team" ("idTeam") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."Match" ADD CONSTRAINT "FK_MATCH_AWAY_TEAM" FOREIGN KEY ("awayTeam") REFERENCES "public"."Team" ("idTeam") ON DELETE NO ACTION ON UPDATE NO ACTION;