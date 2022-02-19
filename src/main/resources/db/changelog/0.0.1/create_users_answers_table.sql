--liquibase formatted sql
--changeset rmartikan:create_users_answers_table endDelimiter:;

create table USERS_ANSWERS (
    user_id bigint not null,
    question_answers_id bigint not null,
    constraint FK_USERS_ANSWERS_USERS foreign key(user_id)
        references USERS(id)
        on delete cascade,
    constraint FK_USERS_ANSWERS_QUESTION_ANSWERS foreign key(question_answers_id)
        references QUESTION_ANSWERS(id)
);