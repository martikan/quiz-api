--liquibase formatted sql
--changeset rmartikan:create_table_questions endDelimiter:;

create table QUESTIONS (
    id bigserial primary key,
    assignment_id bigint not null,
    question_type_id bigint not null,
    title varchar(2000) not null,
    constraint FK_QUESTIONS_QUESTION_TYPES foreign key(question_type_id)
        references QUESTION_TYPES(id),
    constraint FK_QUESTIONS_ASSIGNMENTS foreign key(assignment_id)
        references ASSIGNMENTS(id)
);