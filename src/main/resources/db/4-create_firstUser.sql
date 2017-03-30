BEGIN;

INSERT INTO `user`(sid, login, name, surname, active)
VALUES ('3f7e06822c0541e495078aed80af4b3b', 'test', 'Indiana', 'Jons', true);

INSERT INTO `password`(user_id, password)
VALUES ((select id from `user` where login='test'), '$2a$05$h5/n1etIV2UXX2jLzRUKZeUtUIfLFrfC2LBffWtCP5Uu9bk8shnXi');

COMMIT;