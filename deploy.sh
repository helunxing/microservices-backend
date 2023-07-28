docker push helunxing/event:0.0.1-SNAPSHOT
docker push helunxing/user:0.0.1-SNAPSHOT
docker push helunxing/joininfo:0.0.1-SNAPSHOT
docker push helunxing/postcode:0.0.1-SNAPSHOT

kubectl create deployment event-service --image=helunxing/event:0.0.1-SNAPSHOT
kubectl create deployment user-service --image=helunxing/user:0.0.1-SNAPSHOT
kubectl create deployment joininfo-service --image=helunxing/joininfo:0.0.1-SNAPSHOT
kubectl create deployment postcode-service --image=helunxing/postcode:0.0.1-SNAPSHOT

# TODO: Add Traefik
kubectl expose deployment event-service --type=LoadBalancer --port=8000
kubectl expose deployment user-service --type=LoadBalancer --port=8100
kubectl expose deployment joininfo-service --type=LoadBalancer --port=8200
kubectl expose deployment postcode-service --type=LoadBalancer --port=8020


kubectl set image deployment event-service event-service=helunxing/event:0.0.1-SNAPSHOT
kubectl set image deployment user-service user-service=helunxing/user:0.0.1-SNAPSHOT
kubectl set image deployment joininfo-service joininfo-service=helunxing/joininfo:0.0.1-SNAPSHOT
kubectl set image deployment postcode-service postcode-service=helunxing/postcode:0.0.1-SNAPSHOT

kubectl delete all -l app=event-service
kubectl delete all -l app=user-service
kubectl delete all -l app=joininfo-service
kubectl delete all -l app=postcode-service

event-service=
joininfo-service=
postcode-service=
user-service=
