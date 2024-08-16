create table users
(
    id                    uuid default random_uuid() primary key,
    name                  varchar(100),
    email                 varchar(100) unique,
    password              varchar(12),
    fecha_creacion        timestamp default current_timestamp,
    usuario_creacion      varchar(12),
    fecha_actualizacion   timestamp,
    usuario_actualizacion varchar(12)
);

create table contact_phone
(
    id                    uuid default random_uuid() primary key,
    id_user               uuid not null,
    number_phone          varchar,
    city_code             varchar,
    country_code          varchar,
    fecha_creacion        timestamp default current_timestamp,
    usuario_creacion      varchar(12),
    fecha_actualizacion   timestamp,
    usuario_actualizacion varchar(12),
    foreign key (id_user) references users (id)
);