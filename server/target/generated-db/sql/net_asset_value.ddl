drop table if exists net_asset_value;

create table net_asset_value
(
    scheme_code int not null,
    date timestamp not null,
    net_asset_value float8,
    repurchase_price float8,
    sale_price float8,
    load_id int
);

