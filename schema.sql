drop table if exists user_table;
drop sequence if exists user_id_seq;

create sequence user_id_seq start 1
    increment 1;
create table user_table
(
    id           bigint default nextval('user_id_seq'),
    sub_id       varchar(255)   not null,
    login_key    varchar(255)   not null,
    primary key (id)
);

drop table if exists event_table;
drop sequence if exists event_id_seq;

create sequence event_id_seq start 1
    increment 1;
create table event_table
(
    id      bigint default nextval('event_id_seq'),
    creator_id      bigint         not null,
    title           text           not null,
    date            text           not null,
    time_options    text           not null,
    primary key (id),
    constraint fk_creator_id
        foreign key (creator_id) references user_table (id)
);

drop table if exists join_table;
drop sequence if exists join_id_seq;

create sequence join_id_seq start 1
    increment 1;
create table join_table
(
    id      bigint default nextval('join_id_seq'),
    user_id             bigint        not null,
    event_id            bigint        not null,
    primary key (id),
    constraint fk_user_id
        foreign key (user_id) references user_table (id),
    constraint fk_event_id
        foreign key (event_id) references event_table (id)
);