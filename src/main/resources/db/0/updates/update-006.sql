BEGIN;

USE flightdata;

ALTER TABLE airport zoomlvl int(2) NOT NULL DEFAULT '8';

COMMIT;