USE flightdata;

INSERT INTO plane(create_date, description, `name`, registration, update_date, sid) values
('2017-02-16 13:04:06','plane fyling','plane 1','luftHanza2','2017-02-16 13:04:06', '0941ef7778804f82a77a2fb6839f46f0'),
('2017-02-16 13:04:06','plane 5','plane 2','luftHanza6','2017-04-16 13:04:06', '1fe5f1164b064e4a8866c6fb28111f88'),
('2017-02-16 13:08:06','plane 7','plane 3','luftHanza7','2017-02-16 13:08:06', '77d967e6be574e948843e41c5490f213');

INSERT INTO flight_details (plane_id, course, gps_latitude, gps_longitude, is_actual_position, incoming_time, velocity, distance_traveled,flight_distance,flight_time,remaining_fuel,average_fuel_consumption,is_landed) values
((SELECT id from plane WHERE registration='luftHanza2'), 15, 51, 17, true, NOW(), 800,400,6000,5000,87987,20,false),
((SELECT id from plane WHERE registration='luftHanza6'), 30, 52.19, 17, true, NOW(), 600,400,6000,5000,5000,40,false),
((SELECT id from plane WHERE registration='luftHanza7'), 230, 50.03, 18.2, true, NOW(), 300,400,6000,5000,6678,32,false);
