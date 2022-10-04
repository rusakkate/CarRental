create table if not exists points
(
    id_points   bigserial
        constraint points_pk
            primary key,
    name_points char(10) not null,
    latitude    double precision,
    longitude   double precision
);

alter table points
    owner to postgres;

create unique index if not exists points_id_points_uindex
    on points (id_points);

create table if not exists brand
(
    id_brand   bigserial
        constraint brand_pk
            primary key,
    brand_name char(10) not null
);

alter table brand
    owner to postgres;

create unique index if not exists brand_id_brand_uindex
    on brand (id_brand);

create table if not exists models
(
    id_model   bigserial
        constraint models_pk
            primary key,
    id_brand   bigint not null
        constraint models_brand_id_brand_fk
            references brand
            on update cascade on delete cascade,
    model_name char(10)
);

alter table models
    owner to postgres;

create unique index if not exists models_id_model_uindex
    on models (id_model);

create table if not exists car_types
(
    id_type       bigserial
        constraint car_types_pk
            primary key,
    id_model      bigint           not null
        constraint car_types_models_id_model_fk
            references models
            on update cascade on delete cascade,
    gearbox_type  char(10)         not null,
    number_seats  integer          not null,
    avr_speed     double precision not null,
    engine_volume double precision not null
);

alter table car_types
    owner to postgres;

create unique index if not exists car_types_id_type_uindex
    on car_types (id_type);

create table if not exists users
(
    id_user               bigint        not null
        constraint user_pk
            primary key,
    surname               char(10)                                                                 not null,
    user_name             char(10)                                                                 not null,
    birthday              timestamp(6)                                                             not null,
    driver_license_number char(10)                                                                 not null,
    driver_license_date   timestamp(6)                                                             not null,
    user_login            char(50),
    user_password         char(200),
    email                 char(50),
    latitude              double precision,
    longitude             double precision,
    creation_date         timestamp(6) default CURRENT_TIMESTAMP                                   not null,
    modification_date     timestamp(6)                                                             not null,
    is_deleted            boolean      default false                                               not null
);

alter table users
    owner to postgres;

create unique index if not exists user_id_user_uindex
    on users (id_user);

create index if not exists users_user_name_surname_index
    on users (user_name, surname);

create table if not exists cars
(
    id_car            bigint       not null
        constraint car_pk
            primary key,
    id_type           bigint                                                             not null
        constraint car_car_types_id_type_fk
            references car_types
            on update cascade on delete cascade,
    plate_number      char(10)                                                           not null,
    production_year   integer                                                            not null,
    rating            double precision,
    photo             inet,
    price_day         double precision                                                   not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP                             not null,
    modification_date timestamp(6),
    is_deleted        boolean      default false                                         not null
);

alter table cars
    owner to postgres;

create table if not exists orders
(
    id_order          bigserial
        constraint orders_pk
            primary key,
    id_car            bigint                                 not null
        constraint orders_cars_id_car_fk
            references cars
            on update cascade on delete cascade,
    id_user           bigint                                 not null
        constraint orders_users_id_user_fk
            references users
            on update cascade on delete cascade,
    rental_start_date timestamp(6)                           not null,
    rental_end_date   timestamp(6)                           not null,
    order_price       double precision                       not null,
    route_distance    double precision,
    route             inet,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP
);

alter table orders
    owner to postgres;

create unique index if not exists orders_id_order_uindex
    on orders (id_order);

create table if not exists basing
(
    id_basing bigserial
        constraint basing_pk
            primary key,
    id_point  integer not null
        constraint basing_points_id_points_fk
            references points
            on update cascade on delete cascade,
    id_car    integer not null
        constraint basing_cars_id_car_fk
            references cars
            on update cascade on delete cascade
);

alter table basing
    owner to postgres;

create unique index if not exists basing_id_car_uindex
    on basing (id_car);

create unique index if not exists basing_id_basing_uindex
    on basing (id_basing);

create table if not exists fines
(
    id_fine               bigserial
        constraint fines_pk
            primary key,
    id_order              bigint                                 not null
        constraint fines_orders_id_order_fk
            references orders
            on update cascade on delete cascade,
    number_receipt        integer                                not null,
    sum_fine              double precision                       not null,
    is_payed              boolean      default false             not null,
    creation_date         timestamp(6) default CURRENT_TIMESTAMP not null,
    modification_date     timestamp(6),
    finecharacter         char                                   not null,
    has_been_sent_clients boolean,
    sentdate              timestamp(6)
);

alter table fines
    owner to postgres;

create unique index if not exists fines_id_order_uindex
    on fines (id_order);

create unique index if not exists fines_number_receipt_uindex
    on fines (number_receipt);

create unique index if not exists fines_id_fine_uindex
    on fines (id_fine);

create unique index if not exists car_id_car_uindex
    on cars (id_car);

create table if not exists roles
(
    id_role           serial
        constraint roles_pk
            primary key,
    role_name         char(10)                                  not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp
);

alter table roles
    owner to postgres;

create unique index if not exists roles_id_role_uindex
    on roles (id_role);

create table if not exists user_role
(
    id_user_role bigserial
        constraint user_role_pk
            primary key,
    id_user      bigint not null
        constraint user_role_users_id_user_fk
            references users
            on update cascade on delete cascade,
    id_role      bigint not null
        constraint user_role_roles_id_role_fk
            references roles
            on update cascade on delete cascade
);

alter table user_role
    owner to postgres;

create unique index if not exists user_role_id_user_role_uindex
    on user_role (id_user_role);

create index if not exists user_role_user_id_role_id_index
    on user_role (id_user, id_role);

