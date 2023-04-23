# Microservices Backend Part

## Tasks of Microservices and their API offerings

### [Event Microservice]()

returns events data

provide 

GET `/events`

POST `/event`

GET, PUT, DELETE `/event/{eventId}`

### [Join Info Microservice]()

go语言 graphQL权限检查器

GET, POST, PUT, DELETE `/join/{eventId}`

### [User Info Microservice]()

GET `/user/{userSub}` or `/api/user/{userId}`

### [Postcode Microservice](postcode/README.md)

returns mock address data in a tree format for use by the front end.

provide `/postcode/{queryPostCode}`

## database tables

### User table

user table: user_id, sub_id, login_key.

event table: event_id, event_info_str. 

join info table: user_id, event_id.

## local urls

hello service

<http://localhost:8000/hello-api/{path}>

endpoint service

<http://localhost:8100/point/{path}>

python postcode services

<http://localhost:8002/>

## note for deploy

<https://github.com/in28minutes/spring-microservices-v2/tree/main/05.kubernetes>

push image

`docker push [imageName:Version]`

create new deployment

`kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE`

change to a new image

`kubectl set image deployment hello-world-rest-api hello-world-rest-api=[imageName:Version]`

create deployment.yaml from cloud

`kubectl get deployment hello-world-rest-api -o yaml >> deployment.yaml`

check difference

`kubectl diff -f deployment.yaml`

apply set

`kubectl apply -f deployment.yaml`

delete set

`kubectl delete all -l app=hello-world-rest-api`
