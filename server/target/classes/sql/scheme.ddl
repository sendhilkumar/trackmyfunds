drop table if exists scheme;

create table scheme
(
    code int not null,
    name varchar(500) not null,
    amc varchar(500) not null,
    rta_code varchar(50)
);

