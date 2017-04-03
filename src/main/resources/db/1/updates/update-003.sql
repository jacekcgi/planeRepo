BEGIN;

ALTER TABLE flight_route
ADD COLUMN landed_date DATETIME,
ADD COLUMN flight_phase VARCHAR(32) NOT NULL,
ADD COLUMN flight_distance DOUBLE NOT NULL;

alter table flight_details
CHANGE COLUMN average_fuel_consumption average_fuel_consumption DOUBLE NOT NULL ,
CHANGE COLUMN remaining_fuel remaining_fuel DOUBLE NOT NULL ,
CHANGE COLUMN velocity velocity DOUBLE NOT NULL ;

COMMIT;