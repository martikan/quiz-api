--liquibase formatted sql
--changeset rmartikan:create_groups_assignments_table endDelimiter:;

create table GROUPS_ASSIGNMENTS (
    group_id bigint not null,
    assignment_id bigint not null,
    primary key(group_id, assignment_id),
    constraint FK_GROUPS_ASSIGNMENTS_GROUPS foreign key(group_id)
        references GROUPS(id)
        on delete cascade,
    constraint FK_GROUPS_ASSIGNMENTS_ASSIGNMENTS foreign key(assignment_id)
        references ASSIGNMENTS(id)
        on delete cascade
);