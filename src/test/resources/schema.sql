drop table if exists ivavprp.skills;
drop table if exists ivavprp.achievements;
drop table if exists ivavprp.skills_achievements;
drop schema if exists ivavprp cascade;

create schema ivavprp;

create table ivavprp.skill
(
    id     BIGINT IDENTITY PRIMARY KEY,
    name   varchar(255),
    points int
);

create table ivavprp.achievement
(
    id   BIGINT IDENTITY PRIMARY KEY,
    name varchar(255),
    path varchar(255),
    points int
);

create table ivavprp.skills_achievements
(
    skill_id bigint not null,
    achievement_id bigint not null
)