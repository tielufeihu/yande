create table img_author
(
    img_id  int null,
    user_id int null
);

create table img_clazz
(
    clazz_id   int auto_increment
        primary key,
    clazz_name varchar(128) null,
    grade      int          null,
    master     int          null
);

create table img_collection
(
    id          int auto_increment
        primary key,
    name        varchar(255) null,
    cn_name     varchar(255) null,
    update_time datetime     null
);

create table imginfo
(
    id   int          not null
        primary key,
    path varchar(511) null,
    img  mediumblob   null
);

create table menu
(
    id   int auto_increment
        primary key,
    name varchar(255) not null,
    url  varchar(255) null
);

create table role
(
    role_id    int auto_increment
        primary key,
    role_name  varchar(128) null,
    role_grade int          null
);

create table role_authority
(
    role_id      int null,
    authority_id int null,
    constraint role_authority_ibfk_1
        foreign key (role_id) references role (role_id)
);

create index role_id
    on role_authority (role_id);

create index user_id
    on role_authority (authority_id);

create table tag
(
    id        int auto_increment
        primary key,
    name      varchar(255) null,
    message   varchar(64)  null,
    cn_name   varchar(64)  null,
    clazz     varchar(64)  null,
    img_count int          null
);

create table img_tag
(
    img_id int not null,
    tag_id int not null,
    primary key (tag_id, img_id),
    constraint img_tag_imginfo_id_fk
        foreign key (img_id) references imginfo (id)
            on update cascade on delete cascade,
    constraint img_tag_tag_id_fk
        foreign key (tag_id) references tag (id)
            on update cascade on delete cascade
);

create index img_tag_ibfk_1
    on img_tag (tag_id);

create index imgidyj
    on img_tag (img_id);

create index NameSy
    on tag (name);

create table user
(
    account  int,
    password varchar(128) null,
    name     varchar(128) null,
    role_id  int          null,
    constraint user_ibfk_1
        foreign key (role_id) references role (role_id)
);

create index account
    on user (account);

create index role_id
    on user (role_id);

alter table user
    modify account int auto_increment;

