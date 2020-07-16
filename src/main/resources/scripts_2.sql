create table ivavprp.users
(
    id bigint not null
        constraint users_pkey
            primary key,
    email varchar(255),
    is_active boolean,
    password varchar(255)
);

alter table ivavprp.users owner to postgres;

create table ivavprp.company
(
    about varchar(255),
    name varchar(255),
    photo varchar(255),
    id bigint not null
        constraint company_pkey
            primary key
        constraint fkfydf11ujy2p70ywt0goq9klow
            references ivavprp.users
);

alter table ivavprp.company owner to postgres;

create table ivavprp.student
(
    course integer,
    first_name varchar(255),
    last_name varchar(255),
    photo varchar(255),
    rating integer,
    id bigint not null
        constraint student_pkey
            primary key
        constraint fk3w7xmt19i6a0cr7kp8c80ek40
            references ivavprp.users
);

alter table ivavprp.student owner to postgres;

create table ivavprp.teacher
(
    first_name varchar(255),
    last_name varchar(255),
    photo varchar(255),
    id bigint not null
        constraint teacher_pkey
            primary key
        constraint fkspqy2s83gvl6g8nt5aubls90k
            references ivavprp.users
);

alter table ivavprp.teacher owner to postgres;

