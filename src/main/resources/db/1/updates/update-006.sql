BEGIN;

USE flightdata;

ALTER TABLE airport ADD zoomlvl int(2) NOT NULL DEFAULT '8';

COMMIT;