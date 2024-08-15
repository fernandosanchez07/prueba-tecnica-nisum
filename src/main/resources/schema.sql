create table user(
    id uuid primary key,
    name varchar(100),
    email varchar(100),
    password varchar,
);

create table phone_user(
    id uuid primary key,
    id_user uuid not null,
    number int,
    city_code int,
    country_code int,
    foreign key (id_user) references user(id)
);