BEGIN;

USE flightdata;
ALTER TABLE flight_details add is_landed bit(1) DEFAULT NULL;
UPDATE  flightdata.flight_details set is_landed=false WHERE is_landed IS NULL ;

COMMIT;