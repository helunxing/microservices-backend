# microservices-backend

## note for deploy

build image from maven

`ducker run -p **:** [imageName:Version]`

`docker push [imageName:Version]`

`kubectl set image deployment hello-world-rest-api hello-world-rest-api=[imageName:Version]`

create deployment.yaml from cloud

`kubectl get deployment hello-world-rest-api -o yaml >> deployment.yaml`

check difference

`kubectl diff -f deployment.yaml`

apply set

`kubectl apply -f deployment.yaml`

`kubectl delete all -l app=hello-world-rest-api`

## local urls

hello service

<http://localhost:8000/hello-api>

endpoint service

<http://localhost:8100/end-point/hello>
