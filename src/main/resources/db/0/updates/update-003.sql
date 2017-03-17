BEGIN;

USE flightdata;

ALTER TABLE flight_details MODIFY average_fuel_consumption DOUBLE;
ALTER TABLE airport ADD sid varchar(32) NOT NULL;

COMMIT;