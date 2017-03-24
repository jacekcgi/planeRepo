BEGIN;

ALTER TABLE `airport`
CHANGE COLUMN `altitude` `altitude` DOUBLE NOT NULL ,
CHANGE COLUMN `latitude` `latitude` DOUBLE NOT NULL ,
CHANGE COLUMN `longtitude` `longtitude` DOUBLE NOT NULL ;

ALTER TABLE `flight_details`
CHANGE COLUMN `is_actual_position` `actual_position` TINYINT(1) NOT NULL ;


COMMIT;

