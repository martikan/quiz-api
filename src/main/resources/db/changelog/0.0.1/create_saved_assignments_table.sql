--liquibase formatted sql
--changeset rmartikan:create_saved_assignments_table endDelimiter:;

create table SAVED_ASSIGNMENTS (
    assignment_id bigint primary key,
    created_at timestamp with time zone default current_timestamp,
    updated_at timestamp with time zone,
    constraint FK_SAVED_ASSIGNMENTS_ASSIGNMENTS foreign key(assignment_id)
        references ASSIGNMENTS(id)
);