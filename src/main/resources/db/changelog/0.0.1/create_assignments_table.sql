--liquibase formatted sql
--changeset rmartikan:create_assignments_table endDelimiter:;

create table ASSIGNMENTS (
    id bigserial primary key,
    title varchar(255) unique not null,
    description varchar(4000),
    created_at timestamp with time zone default current_timestamp,
    created_by bigint not null,
    valid_before timestamp with time zone,
    is_closed bool default false,
    constraint FK_ASSIGNMENTS_USERS foreign key(created_by)
        references USERS(id)
);