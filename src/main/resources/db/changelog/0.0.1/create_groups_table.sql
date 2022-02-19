--liquibase formatted sql
--changeset rmartikan:create_groups_table endDelimiter:;

create table GROUPS (
    id bigserial primary key,
    name varchar(100) unique not null,
    description varchar(4000)
);