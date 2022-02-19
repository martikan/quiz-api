--liquibase formatted sql
--changeset rmartikan:create_question_answers_table endDelimiter:;

create table QUESTION_ANSWERS (
    id bigserial primary key,
    question_id bigint not null,
    description varchar(4000),
    is_right_answer bool default false,
    constraint FK_QUESTION_ANSWERS_QUESTIONS foreign key(question_id)
        references QUESTIONS(id)
);