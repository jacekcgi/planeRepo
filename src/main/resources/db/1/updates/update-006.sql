begin;

SET foreign_key_checks = 0;

ALTER TABLE `user` CHANGE COLUMN id id bigint(20) AUTO_INCREMENT;
ALTER TABLE `password` CHANGE COLUMN id id bigint(20) AUTO_INCREMENT;

SET foreign_key_checks = 1;

commit;