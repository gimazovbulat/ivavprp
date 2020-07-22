drop table if exists practice.skills;
drop table if exists practice.achievements;
drop table if exists practice.skills_achievements;
drop schema if exists practice;

create schema practice;

create table practice.skills
(
    id     BIGINT IDENTITY PRIMARY KEY,
    name   varchar(255),
    points int
);

create table practice.achievements
(
    id   BIGINT IDENTITY PRIMARY KEY,
    name varchar(255),
    path varchar(255)
);

create table practice.skills_achievements
(
    skill_id bigint not null,
    achievement_id bigint not null
)