drop table if exists nav_load;

create table nav_load
(
    id int not null,
    load_time timestamp,
    latest_nav_date timestamp,
    max_occuring_date timestamp
);

