create sequence users_id_user_seq
    as integer;

alter sequence users_id_user_seq owner to postgres;

alter sequence users_id_user_seq owned by users.id_user;


