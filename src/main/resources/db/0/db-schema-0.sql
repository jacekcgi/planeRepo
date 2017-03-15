BEGIN;

USE flightdata;

CREATE TABLE plane (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  sid varchar(32) NOT NULL,
  create_date datetime DEFAULT NULL,
  description text,
  `name` varchar(255) DEFAULT NULL,
  registration varchar(255) NOT NULL,
  update_date datetime DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_plane_registration (registration)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX plane_sid_index ON plane (sid) USING BTREE;
CREATE INDEX plane_create_date_index ON plane (create_date) USING BTREE;
CREATE INDEX plane_registration_index ON plane (registration) USING BTREE;
CREATE INDEX plane_update_date_index ON plane (update_date) USING BTREE;

CREATE TABLE `flight_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `average_fuel_consumption` double DEFAULT NULL,
  `course` double DEFAULT NULL,
  `distance_traveled` double DEFAULT NULL,
  `flight_distance` double DEFAULT NULL,
  `flight_time` bigint(20) DEFAULT NULL,
  `gps_latitude` double DEFAULT NULL,
  `gps_longitude` double DEFAULT NULL,
  `incoming_time` datetime DEFAULT NULL,
  `is_actual_position` bit(1) DEFAULT NULL,
  `remaining_fuel` double DEFAULT NULL,
  `velocity` float DEFAULT NULL,
  `plane_id` bigint(20) NOT NULL,
  `is_landed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (plane_id) REFERENCES plane (id) ON DELETE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE INDEX flight_details_incoming_time_index ON flight_details (incoming_time) USING BTREE;

COMMIT;

