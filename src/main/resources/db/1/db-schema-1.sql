BEGIN;

USE flightdata;

CREATE TABLE plane (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  sid varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  registration varchar(255) NOT NULL,
  description text,
  create_date DATETIME not NULL,
  update_date DATETIME not NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_plane_registration (registration)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX plane_sid_index ON plane (sid) USING BTREE;
CREATE INDEX plane_create_date_index ON plane (create_date) USING BTREE;
CREATE INDEX plane_registration_index ON plane (registration) USING BTREE;
CREATE INDEX plane_update_date_index ON plane (update_date) USING BTREE;

drop table if exists airport;
CREATE TABLE `airport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `altitude` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `daylight_saving_time` varchar(255) DEFAULT NULL,
  `iata_code` varchar(255) DEFAULT NULL,
  `icao_code` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) NOT NULL,
  `longtitude` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `source` varchar(255) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `tz_database_time_zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

CREATE INDEX airport_city_index ON airport (city) USING BTREE;
CREATE INDEX airport_name_index ON airport (name) USING BTREE;

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
  PRIMARY KEY (`id`),
  FOREIGN KEY (flight_route_id)
        REFERENCES flight_route(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;

