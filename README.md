# Microservices backend part

## Tasks of Microservices and their API offerings

### [Event Microservice]()

returns events data

GET `/events`

POST `/event`

GET, PUT, DELETE `/event/{eventId}`

### [Join Info Microservice]()

GET, POST, PUT, DELETE `/join/{eventId}`

GET `/user/joined/{userSub}`

### [Identity Check]()

go语言 graphQL权限检查器

GET `/key/{userKey}`

### [Postcode Microservice](postcode/README.md)

returns mock address data in a tree format for use by the front end.

provide `/postcode/{queryPostCode}`

## Database tables

### User table

```
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
```

### event table

```
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
```

### join info table

```
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
```

## Local URLs

hello service

<http://localhost:8000/hello-api/{path}>

endpoint service

<http://localhost:8100/point/{path}>

python postcode services

<http://localhost:8002/>

## Note for deploy

<https://github.com/in28minutes/spring-microservices-v2/tree/main/05.kubernetes>

push image

`docker push [imageName:Version]`

create new deployment

`kubectl create deployment hello-service --image=helunxing/hello-world-rest-api:0.0.1-SNAPSHOT`

expose deployment

`kubectl expose deployment hello-service --type=LoadBalancer --port=8080`

change to a new image

`kubectl set image deployment hello-service hello-service=[imageName:Version]`

create deployment.yaml from cloud

`kubectl get deployment hello-service -o yaml >> deployment.yaml`

check difference

`kubectl diff -f deployment.yaml`

apply set

`kubectl apply -f deployment.yaml`

delete set

`kubectl delete all -l app=hello-world-rest-api`
