BEGIN;

SET SQL_SAFE_UPDATES = 0;

SET foreign_key_checks = 0;

DELETE FROM `plane`;

delete from flight_details;

drop table if exists flight_route;
CREATE TABLE `flight_route` (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  sid varchar(32) NOT NULL,
  source_id bigint not null,
  destination_id bigint not null,
  plane_id bigint not null,
  start_date DATETIME not null,
  incoming_date DATETIME not null,
  PRIMARY KEY (`id`),
  FOREIGN KEY (source_id)
        REFERENCES airport(id)
        ON DELETE no action,
        FOREIGN KEY (destination_id)
        REFERENCES airport(id)
        ON DELETE no action,
        FOREIGN KEY (plane_id)
        REFERENCES plane(id)
        ON DELETE no action
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX flight_route_sid_index ON flight_route (sid) USING BTREE;
CREATE INDEX flight_route_start_date_index ON flight_route (start_date) USING BTREE;
CREATE INDEX flight_route_incoming_date_index ON flight_route (incoming_date) USING BTREE;

drop table if exists flight_details;
CREATE TABLE `flight_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `average_fuel_consumption` double DEFAULT NULL,
  `distance_traveled` double not NULL,
  `gps_latitude` double not NULL,
  `gps_longitude` double not NULL,
  `remaining_fuel` double DEFAULT NULL,
  `velocity` float not NULL,
  `flight_route_id` bigint(20) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `actual_position` boolean NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (flight_route_id)
        REFERENCES flight_route(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET foreign_key_checks = 1;

COMMIT;

