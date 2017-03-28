BEGIN;

ALTER TABLE `airport`
CHANGE COLUMN `altitude` `altitude` DOUBLE NOT NULL ,
CHANGE COLUMN `latitude` `latitude` DOUBLE NOT NULL ,
CHANGE COLUMN `longtitude` `longtitude` DOUBLE NOT NULL ;

COMMIT;