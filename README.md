# microservices-backend

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

## local urls

hello service

<http://localhost:8000/hello-api>

endpoint service

<http://localhost:8100/end-point/hello>
