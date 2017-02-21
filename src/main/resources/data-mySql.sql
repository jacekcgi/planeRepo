insert into flightData.plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:04:06','plane fyling','plane 1','luftHanza2','2017-02-16 13:04:06');

insert into flightData.plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:04:06','plane 5','plane 3','luftHanza6','2017-04-16 13:04:06');

insert into flightData.plane(create_date, description, `name`, registration, update_date)
values ('2017-02-16 13:08:06','plane 7','plane 3','luftHanza6','2017-02-16 13:08:06');

INSERT INTO flightData.flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position) values (1,15,"17","51",true);
INSERT INTO flightData.flight_details (plane_id,course,gps_latitude,gps_longitude,is_actual_position) values (2,40,"21","52",true);
