drop function calculate_order_amount(start_date_param timestamp without time zone,
    end_date_param timestamp without time zone, id_car_param bigint);

create function calculate_order_amount(start_date_param timestamp without time zone,
    end_date_param timestamp without time zone, id_car_param bigint)
    returns double precision
    language sql
    as
$$
select (cast(date_part ('day', end_date_param - start_date_param) as int) + 1)
           * carrental.cars.price_day
from carrental.cars
where carrental.cars.id_car = id_car_param;
$$;

alter function calculate_order_amount(timestamp, timestamp, bigint) owner to postgres;


drop function change_is_free(id_car_param bigint, start_date_param timestamp without time zone,
    end_date_param timestamp without time zone);

create function change_is_free(id_car_param bigint, start_date_param timestamp without time zone,
    end_date_param timestamp without time zone)
    returns integer
    language sql
    as
$$
update carrental.cars_schedule set is_free = 'false', modification_date = CURRENT_TIMESTAMP
where carrental.cars_schedule.id_car = id_car_param
  and carrental.cars_schedule.use_day >= start_date_param and
        carrental.cars_schedule.use_day <= end_date_param
    returning 1;
$$;

alter function change_is_free(bigint, timestamp, timestamp) owner to postgres;


drop function check_free_period(id_car_param bigint, start_date_param timestamp without time zone,
    end_date_param timestamp without time zone);

    create function check_free_period(id_car_param bigint, start_date_param timestamp without time zone,
    end_date_param timestamp without time zone)
    returns boolean
    language sql
    as
$$
select count (carrental.cars_schedule.is_free)  = 0
from carrental.cars_schedule
where carrental.cars_schedule.is_free = 'true'
  and carrental.cars_schedule.id_car = id_car_param
  and carrental.cars_schedule.use_day >= start_date_param and
        carrental.cars_schedule.use_day <= end_date_param;
$$;

alter function check_free_period(bigint, timestamp, timestamp) owner to postgres;

drop function find_free_cars_for_period(start_date_param timestamp without time zone,
    end_date_param timestamp without time zone);

    create function find_free_cars_for_period(start_date_param timestamp without time zone,
    end_date_param timestamp without time zone)
    returns SETOF integer
    language sql
    as
    $$
select distinct carrental.cars_schedule.id_car
from carrental.cars_schedule
where carrental.cars_schedule.is_free = 'true'
  and carrental.cars_schedule.use_day >= start_date_param
  and carrental.cars_schedule.use_day <= end_date_param;
$$;

alter function find_free_cars_for_period(timestamp, timestamp) owner to postgres;