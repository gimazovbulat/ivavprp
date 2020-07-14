-- auto-generated definition
create table achievement
(
    id     bigserial    not null
        constraint achievements_pk
            primary key,
    name   varchar(255) not null,
    path   varchar(255) not null,
    points integer      not null
);

alter table achievement
    owner to postgres;

create unique index achievements_id_uindex
    on achievement (id);

-- auto-generated definition
create table skill
(
    id     bigserial not null
        constraint skills_pk
            primary key,
    name   varchar,
    points integer
);

alter table skill
    owner to postgres;

-- auto-generated definition
create table skills_achievements
(
    skill_id       bigint not null,
    achievement_id bigint not null
);

alter table skills_achievements
    owner to postgres;


-- auto-generated definition
create table vacancy
(
    id              bigserial    not null
        constraint vacancy_pk
            primary key,
    employment_type varchar(100) not null,
    text            text,
    time            timestamp    not null,
    work_schedule   varchar(50)  not null,
    name            varchar(50)  not null,
    min             integer,
    max             integer
);

alter table vacancy
    owner to postgres;

create unique index vacancy_id_uindex
    on vacancy (id);


-- auto-generated definition
create table skills_vacancies
(
    skill_id   bigint not null,
    vacancy_id bigint not null
);

alter table skills_vacancies
    owner to postgres;

