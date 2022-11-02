create table if not exists users
(
    id_user               serial
        constraint user_pk
            primary key,
    surname               varchar(50)  default 'surname'::character varying                        not null,
    user_name             varchar(20)  default 'name'::character varying                           not null,
    birthday              timestamp(6)                                                             not null,
    driver_license_number varchar(20),
    driver_license_date   timestamp(6),
    user_login            varchar(100),
    user_password         varchar(200) default 'default_password'::character varying,
    email                 varchar(50),
    latitude              double precision,
    longitude             double precision,
    creation_date         timestamp(6) default CURRENT_TIMESTAMP                                   not null,
    modification_date     timestamp(6) default CURRENT_TIMESTAMP                                   not null,
    is_deleted            boolean      default false                                               not null,
    activation_code       varchar(100)
);

alter table users
    owner to postgres;

create unique index if not exists user_id_user_uindex
    on users (id_user);

create index if not exists users_user_name_surname_index
    on users (user_name, surname);

create table if not exists cars
(
    id_car            serial
        constraint car_pk
            primary key,
    brand             varchar(50)                                                        not null,
    model             varchar(50)                                                        not null,
    plate_number      varchar(10)                                                        not null,
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

create unique index if not exists cars_id_car_uindex
    on cars (id_car);


create table if not exists orders
(
    id_order          bigserial
    constraint orders_pk
    primary key,

    id_user     bigint not null
    constraint orders_users_id_user_fk
    references users
    on update cascade on delete cascade,

    id_car      bigint not null
    constraint orders_cars_id_car_fk
    references cars
    on update cascade on delete cascade,

    rental_start_date timestamp(6)                           not null,
    rental_end_date   timestamp(6)                           not null,
    order_price       double precision                       not null,
    route_distance    double precision,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP

    );

alter table orders
    owner to postgres;

create unique index if not exists orders_id_order_uindex
    on orders (id_order);

create index if not exists orders_id_user_id_car_index
    on orders (id_user, id_car);

create unique index if not exists car_id_car_uindex
    on cars (id_car);

create table if not exists roles
(
    id_role           serial
        constraint roles_pk
            primary key,
    role_name         varchar(50)                               not null,
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

create table if not exists cars_schedule
(
    id_car_schedule   bigserial
        constraint cars_schedule_pk
            primary key,
    id_car            bigint                                    not null
        constraint cars_schedule_cars_id_car_fk
            references cars
            on update cascade on delete cascade,
    use_day           timestamp(6)                              not null,
    is_free           boolean      default true                 not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6)
);

create index cars_schedule_id_car_index
    on cars_schedule (id_car);

create unique index cars_schedule_id_car_schedule_uindex
    on cars_schedule (id_car_schedule);

alter table cars_schedule
    owner to postgres;