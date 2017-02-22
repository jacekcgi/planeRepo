insert into flightData.plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:04:06','plane fyling','plane 1','luftHanza2','2017-02-16 13:04:06');

insert into flightData.plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:04:06','plane 5','plane 3','luftHanza6','2017-04-16 13:04:06');

insert into flightData.plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:08:06','plane 7','plane 3','luftHanza6','2017-02-16 13:08:06');

INSERT INTO flightData.flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position,incoming_time) values (1,15,"51","17",true,NOW());
INSERT INTO flightData.flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position,incoming_time) values (2,30,"52.19","17",true,NOW());
INSERT INTO flightData.flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position,incoming_time) values (3,30,"72.03","89.2",true,NOW());
