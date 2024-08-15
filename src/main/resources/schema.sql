create table users(
    id uuid primary key,
    name varchar(100),
    email varchar(100) unique,
    user_password varchar(12)
);

create table phone_user(
    id uuid primary key,
    id_user uuid not null,
    number_phone int,
    city_code int,
    country_code int,
    foreign key (id_user) references users(id)
);