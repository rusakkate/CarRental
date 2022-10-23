INSERT INTO brand (brand_name)
VALUES ('toyota');
INSERT INTO brand (brand_name)
VALUES ('audi');
INSERT INTO brand (brand_name)
VALUES ('volvo');

INSERT INTO models (id_brand, model_name)
VALUES ('1', 'yaris');
INSERT INTO models (id_brand, model_name)
VALUES ('1', 'corolla');
INSERT INTO models (id_brand, model_name)
VALUES ('1', 'land cruser');
INSERT INTO models (id_brand, model_name)
VALUES ('2', 'q4');
INSERT INTO models (id_brand, model_name)
VALUES ('2', 'q5');
INSERT INTO models (id_brand, model_name)
VALUES ('2', 'sq8');
INSERT INTO models (id_brand, model_name)
VALUES ('3', 'xc40');
INSERT INTO models (id_brand, model_name)
VALUES ('3', 'xc60');
INSERT INTO models (id_brand, model_name)
VALUES ('3', 'xc90');

INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('1', 'meh', '5', '90', '1.0');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('1', 'meh', '2', '110', '1.5');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('2', 'aut', '5', '130', '2.0');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('3', 'aut', '5', '130', '2.5');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('4', 'meh', '5', '130', '1.5');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('4', 'aut', '5', '110', '1.3');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('5', 'aut', '5', '150', '2.5');
INSERT INTO car_types (id_model, gearbox_type, number_seats, avr_speed, engine_volume)
VALUES ('5', 'meh', '5', '150', '2.5');

INSERT INTO cars (id_type, plate_number, production_year, rating, price_day, creation_date, modification_date, is_deleted)
VALUES ('1', '8635 PA-7', '2022', '10', '50', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000', 'false');
INSERT INTO cars (id_type, plate_number, production_year, rating,price_day, creation_date, modification_date, is_deleted)
VALUES ('1', '5698 OA-7', '2020', '9', '30', '2020-10-10 21:26:28.279000', '2021-11-10 21:26:28.279000', 'false');
INSERT INTO cars (id_type, plate_number, production_year, rating, price_day, creation_date, modification_date, is_deleted)
VALUES ('1', '7788 PA-7', '2021', '9', '40', '2021-11-10 21:26:28.279000', '2021-11-10 21:26:28.279000', 'false');
INSERT INTO cars (id_type, plate_number, production_year, rating,  price_day, creation_date, modification_date, is_deleted)
VALUES ('5', '0007 PA-7', '2021', '10', '100', '2021-11-10 21:26:28.279000', '2021-11-10 21:26:28.279000', 'false');
INSERT INTO cars (id_type, plate_number, production_year, rating,  price_day, creation_date, modification_date, is_deleted)
VALUES ('5', '0097 OA-7', '2022', '10',  '150', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000', 'false');

INSERT INTO users (surname, user_name, birthday, driver_license_number, driver_license_date,
                   user_login, user_password, email,
                   creation_date, modification_date,is_deleted)
VALUES ('rusak', 'kate', '1989-06-04 21:26:28.279000', 'IIIJJJ126P', '2012-11-10 21:26:28.279000',
        'rusakkate', '12345', 'melitrica_red@mail.ru',
        '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000', 'false');
INSERT INTO users (surname, user_name, birthday, driver_license_number, driver_license_date,
                   user_login, user_password, email,
                   creation_date, modification_date,is_deleted)
VALUES ('kotik', 'sofia', '1989-06-04 21:26:28.279000', 'MAYMAY26P', '2012-11-10 21:26:28.279000',
        'sofiakotik', '12345', 'melitrica_red@mail.ru',
        '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000', 'false');
INSERT INTO users (surname, user_name, birthday, driver_license_number, driver_license_date,
                   user_login, user_password, email,
                   creation_date, modification_date,is_deleted)
VALUES ('ivanov', 'ivan', '1989-06-04 21:26:28.279000', 'MAYMAY26P', '2012-11-10 21:26:28.279000',
        'ivanivanov', '12345', 'melitrica_red@mail.ru',
        '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000', 'false');

INSERT INTO roles (role_name, creation_date, modification_date)
VALUES ('ROLE_ADMIN', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO roles (role_name, creation_date, modification_date)
VALUES ('ROLE_USER', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO roles (role_name, creation_date, modification_date)
VALUES ('ROLE_MODERATOR', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO roles (role_name, creation_date, modification_date)
VALUES ('ROLE_ANONYMOUS', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');

INSERT INTO user_role (id_user, id_role)
VALUES ('1', '1');
INSERT INTO user_role (id_user, id_role)
VALUES ('1', '2');
INSERT INTO user_role (id_user, id_role)
VALUES ('2', '2');
INSERT INTO user_role (id_user, id_role)
VALUES ('3', '2');

INSERT INTO orders (id_user, id_car, rental_start_date, rental_end_date, order_price,
                    route_distance, creation_date, modification_date)
VALUES ('1', '1', '2022-10-10 21:26:28.279000', '2022-10-12 21:24:28.279000', '100',
        '150', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO orders (id_user, id_car, rental_start_date, rental_end_date, order_price,
                    route_distance, creation_date, modification_date)
VALUES ('1', '2', '2022-10-15 21:26:28.279000', '2022-10-20 21:24:28.279000', '200',
        '150', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO orders (id_user, id_car, rental_start_date, rental_end_date, order_price,
                    route_distance, creation_date, modification_date)
VALUES ('2', '1', '2022-10-15 21:26:28.279000', '2022-10-17 21:24:28.279000', '200',
        '150', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO orders (id_user, id_car, rental_start_date, rental_end_date, order_price,
                    route_distance, creation_date, modification_date)
VALUES ('2', '3', '2022-10-25 21:26:28.279000', '2022-10-27 21:24:28.279000', '400',
        '150', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO orders (id_user, id_car, rental_start_date, rental_end_date, order_price,
                    route_distance, creation_date, modification_date)
VALUES ('3', '4', '2022-10-25 21:26:28.279000', '2022-10-27 21:24:28.279000', '400',
        '150', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');
INSERT INTO orders (id_user, id_car, rental_start_date, rental_end_date, order_price,
                    route_distance, creation_date, modification_date)
VALUES ('3', '5', '2022-11-01 21:26:28.279000', '2022-11-05 21:24:28.279000', '700',
        '250', '2022-10-10 21:26:28.279000', '2022-10-10 21:26:28.279000');