/*
Created: 12/31/2017
Modified: 1/28/2018
Model: PostgreSQL 9.5
Database: PostgreSQL 9.5
*/


-- Create tables section -------------------------------------------------

-- Table movie

CREATE TABLE "movie"(
 "id" BigSerial NOT NULL,
 "original_title" Character varying(300) NOT NULL,
 "production_year" Numeric(4,0) NOT NULL,
 "release_date" Date,
 "poster" Bytea,
 "overview" Text,
 "runtime" Numeric(5,0),
 "budget" Numeric(15,2),
 "rating" Numeric(3,1),
 "public_rating" Numeric(3,1),
 "certificate" Integer,
 "aspect_ratio" Character varying(20),
 "budget_currency" Character(2),
 "dvd_release" Date,
 "main_commentary" Text,
 "insert_user" Character varying NOT NULL
        CONSTRAINT "movie_insert_user_email" CHECK (insert_user ~* '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$'),
 "created_at" Bigint NOT NULL,
 "update_user" Character varying
        CONSTRAINT "movie_update_user_email" CHECK (update_user ~* '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$'),
 "updated_at" Bigint
)
;

-- Create indexes for table movie

CREATE INDEX "ix_movie_certificate" ON "movie" ("certificate")
;

CREATE INDEX "ix_movie_budget" ON "movie" ("budget_currency")
;

-- Add keys for table movie

ALTER TABLE "movie" ADD CONSTRAINT "Key1" PRIMARY KEY ("id")
;

-- Table country

CREATE TABLE "country"(
 "code" Character(2) NOT NULL,
 "name" Character varying(80) NOT NULL,
 "currency_iso_code" Character varying(3),
 "currency_symbol" Character varying(5),
 "currency_name" Character varying(50)
)
;

-- Add keys for table country

ALTER TABLE "country" ADD CONSTRAINT "Key2" PRIMARY KEY ("code")
;

-- Table certificate

CREATE TABLE "certificate"(
 "id" Serial NOT NULL,
 "description" Character varying(50) NOT NULL,
 "country" Character(2)
)
;

-- Create indexes for table certificate

CREATE INDEX "ix_certificate_country" ON "certificate" ("country")
;

-- Add keys for table certificate

ALTER TABLE "certificate" ADD CONSTRAINT "Key3" PRIMARY KEY ("id")
;
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "certificate" ADD CONSTRAINT "country_certificate" FOREIGN KEY ("country") REFERENCES "country" ("code") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "movie" ADD CONSTRAINT "movie_certificate" FOREIGN KEY ("certificate") REFERENCES "certificate" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "movie" ADD CONSTRAINT "fk_movie_budget" FOREIGN KEY ("budget_currency") REFERENCES "country" ("code") ON DELETE NO ACTION ON UPDATE NO ACTION
;




