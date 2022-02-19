--liquibase formatted sql
--changeset rmartikan:create_table_question_types endDelimiter:;

create table QUESTION_TYPES (
    id bigserial primary key,
    name varchar(50) unique not null
);