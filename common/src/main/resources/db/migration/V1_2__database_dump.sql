INSERT INTO cars (brand, model, plate_number, production_year, rating, price_day, creation_date, modification_date)
VALUES ('toyota', 'yaris', '8635 PA-7', '2022', '10', '50', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('audi', 'sq8', '5698 OA-7', '2020', '9', '30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('toyota', 'yaris', '7788 PA-7', '2021', '9', '40', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('volvo', 'xc60', '0007 PA-7', '2021', '10', '100', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('toyota', 'yaris', '0097 OA-7', '2022', '10',  '150', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO users (surname, user_name, birthday, driver_license_number, driver_license_date,
                   user_login, user_password, email,
                   creation_date, modification_date)
VALUES ('rusak', 'kate', '1989-06-04 21:26:28.279000', 'IIIJJJ126P', '2012-11-10 21:26:28.279000',
        'rusakkate', '$2a$06$406yD5V6QDIIyGrpR3ejpOvmW817vJ7yhMDBAskuBT9glB.9RPI0m', 'melitrica_red@mail.ru',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('kotik', 'sofia', '1989-06-04 21:26:28.279000', 'MAYMAY26P', '2012-11-10 21:26:28.279000',
        'sofiakotik', '$2a$06$406yD5V6QDIIyGrpR3ejpOvmW817vJ7yhMDBAskuBT9glB.9RPI0m', 'sofiakotik@mail.ru',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('ivanov', 'ivan', '1989-06-04 21:26:28.279000', 'MAYMAY26P', '2012-11-10 21:26:28.279000',
        'ivanivanov', '$2a$06$406yD5V6QDIIyGrpR3ejpOvmW817vJ7yhMDBAskuBT9glB.9RPI0m', 'ivanivanov@mail.ru',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO roles (role_name, creation_date, modification_date)
VALUES ('ROLE_ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ROLE_MODERATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('ROLE_ANONYMOUS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_role (id_user, id_role)
VALUES ('1', '1'),
       ('1', '2'),
       ('2', '2'),
       ('3', '2');

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

INSERT INTO cars_schedule (id_car, use_day, creation_date, modification_date)
VALUES ('1', generate_series('2022-11-01 00:00'::timestamp, '2023-01-01 00:00', '24 hours'),
        '2022-10-30 21:24:28.279000', '2022-10-30 21:24:28.279000');
INSERT INTO cars_schedule (id_car, use_day, creation_date, modification_date)
VALUES ('2', generate_series('2022-11-01 00:00'::timestamp, '2023-01-01 00:00', '24 hours'),
        '2022-10-30 21:24:28.279000', '2022-10-30 21:24:28.279000');
INSERT INTO cars_schedule (id_car, use_day, creation_date, modification_date)
VALUES ('3', generate_series('2022-11-01 00:00'::timestamp, '2023-01-01 00:00', '24 hours'),
        '2022-10-30 21:24:28.279000', '2022-10-30 21:24:28.279000');
INSERT INTO cars_schedule (id_car, use_day, creation_date, modification_date)
VALUES ('4', generate_series('2022-11-01 00:00'::timestamp, '2023-01-01 00:00', '24 hours'),
        '2022-10-30 21:24:28.279000', '2022-10-30 21:24:28.279000');
INSERT INTO cars_schedule (id_car, use_day, creation_date, modification_date)
VALUES ('5', generate_series('2022-11-01 00:00'::timestamp, '2023-01-01 00:00', '24 hours'),
        '2022-10-30 21:24:28.279000', '2022-10-30 21:24:28.279000');