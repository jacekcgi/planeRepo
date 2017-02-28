insert into plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:04:06','plane fyling','plane 1','luftHanza2','2017-02-16 13:04:06');

insert into plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:04:06','plane 5','plane 3','luftHanza6','2017-04-16 13:04:06');

insert into plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:08:06','plane 7','plane 3','luftHanza6','2017-02-16 13:08:06');

INSERT INTO flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position,incoming_time,velocity) values (1,15,"51","17",true,NOW(),800);
INSERT INTO flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position,incoming_time,velocity) values (2,30,"52.19","17",true,NOW(),600);
INSERT INTO flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position,incoming_time,velocity) values (3,230,"50.03","18.2",true,NOW(),300);
