create table file
(
    file_id      double precision,
    name         varchar,
    created_date varchar,
    file_size    varchar,
    file_url     varchar
);

alter table file
    owner to akash;