BEGIN;

USE flightdata;
ALTER TABLE flight_details add is_landed bit(1) DEFAULT NULL;

COMMIT;