drop table if exists transaction;

create table transaction
(
    id int not null,
    date timestamp not null,
    scheme_code int not null,
    amount float8 not null,
    description varchar(500),
    units float8,
    price float8,
    unitBalance float8,
    rta_code varchar(50),
    schemeName varchar(500),
    folioNumber varchar(100),
    registrar varchar(100),
    source varchar(100)
);

