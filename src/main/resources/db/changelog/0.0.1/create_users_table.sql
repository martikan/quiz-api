--liquibase formatted sql
--changeset rmartikan:create_users_table splitStatements:true endDelimiter:;

create table USERS (
    id bigserial primary key,
    email varchar(255) unique not null,
    password varchar(66) not null,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    phone varchar (100),
    created_at timestamp with time zone default current_timestamp,
    updated_at timestamp with time zone,
    last_login_at timestamp with time zone,
    is_registered bool default false
);

create index idx_users_names on USERS(first_name, last_name);

-- Create admin user.
insert into USERS(email, password, first_name, last_name, is_registered) values('admin@info.com', '$2a$10$aki3VEXm/xh4JxgKbOKK9OIFaoytr39uZVSZg4zestru/i3NATUEy', 'Admin', 'Admin', true);