--liquibase formatted sql
--changeset rmartikan:create_users_groups_table endDelimiter:;

create table USERS_GROUPS (
    user_id bigint not null,
    group_id bigint not null,
    primary key(user_id, group_id),
    constraint FK_USERS_GROUPS_USERS foreign key(user_id)
        references USERS(id),
    constraint FK_USERS_GROUPS_GROUPS foreign key(group_id)
        references GROUPS(id)
        on delete cascade
);