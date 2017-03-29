begin;

CREATE TABLE `user` (
	id bigint(20) PRIMARY KEY,
	sid varchar(32) NOT NULL,
    active boolean NOT NULL,
    login varchar(32) NOT NULL,
    `name` varchar(32) NOT NULL,
    surname varchar(32) NOT NULL,
    UNIQUE KEY UK_user_sid (sid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX user_sid_index ON `user` (sid) USING BTREE;
CREATE INDEX user_active_index ON `user` (active) USING BTREE; #WHERE active IS FALSE
CREATE INDEX user_name_index ON `user` (`name`) USING BTREE;
CREATE INDEX user_surname_index ON `user` (surname) USING BTREE;

CREATE TABLE `password` (
	id bigint(20) PRIMARY KEY,
	`password` varchar(256) NOT NULL,
    user_id bigint(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES `user`(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE INDEX password_user_id_index ON `password`(user_id ) USING BTREE;

commit;