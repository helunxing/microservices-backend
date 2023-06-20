# Microservices backend part

This introduction is for the Time Agreement web app project,
specifically its Backend part.

The project is deployed at: <https://hlx.codes>

Other parts introduction for this project:

[Frontend](https://github.com/helunxing/heroku-page/tree/release/client)

[Backend for Frontend part & overall introduction](https://github.com/helunxing/heroku-page)

## Tasks of Microservices and their API offerings

Microservices backend part contains 5 microservices, here is their tasks and API offerings:

### [Event Microservice](./event)

Provide:

GET `/events`

POST `/event`

GET, PUT, DELETE `/event/{eventId}`

### [User Microservice](./user)

User microservice return users data and handle change request.

Default port number can find in [here](./README.md#local-urls).

Unit test for every http api can find in [here](./user/req.http).

Provide:

GET `/users`

POST `/user`

GET, PUT, DELETE `/user/{userId}`

### [Join Info Microservice](./joinInfo)

Provide:

POST `/join`

GET, PUT, DELETE `/join/{eventId}`

GET `/user/joined/{userSub}`

### [Identity Check](./identityCheck)

go语言 graphQL权限检查器

Provide:

GET `/key/{userKey}`

### [Postcode Microservice](./postcode)

returns mock address data in a tree format for use by the front end.

Provide:

GET `/postcode/{queryPostCode}`

## Database tables

Contain: User table, Event table, Join info table

SQL file: [schema.sql](schema.sql)

## Local URLs

event service: <http://localhost:8000/>

user service: <http://localhost:8100/>

join info service: <http://localhost:8200/>

python postcode services: <http://localhost:8020/>

identity check service: <http://localhost:8030/>

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
