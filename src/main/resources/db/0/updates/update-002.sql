BEGIN;

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

COMMIT;