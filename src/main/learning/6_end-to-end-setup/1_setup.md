# Running Elastic 2 node cluster on Rancher using TLS + HTTP + HELM + Secret


## Step 1 - Follow cert creation  - 1_cert.md

## Step 2 - Follow kubernetes secret creation for certs - 3_Kubernetes/2_kube_secret.md

## Step 3 - Setup Files in elastic-tls folder

## run command to install helm

helm install elastic ./elastic-tls

-- we should have only one service in elastic-tls folder. I have added 2 for different purpose

## verify the helm

### Check what Helm actually installed

helm list -n default

kubectl get all -n default

## Verify all resources

kubectl get pods -l app=elastic

## login to pod
kubectl exec -it elastic-0 -- /bin/sh

## check cluster health
curl --cacert ./config/certs/ca/ca.crt --cert ./config/certs/http/tls.crt --key ./config/certs/http/tls.key -u elastic:changeme https://elastic:9200/_cluster/health?pretty


# Test using java app local

## 1- copy cluster ca.crt in to local

kubectl exec elastic-0 -- cp /usr/share/elasticsearch/config/certs/ca/..data/ca.crt /tmp/ca.crt

kubectl cp elastic-0:/tmp/ca.crt src/main/resources/ca.crt


## Test using java app docker image on cluster

### clean install
### docker build -t my-spring-app:latest .
### kubectl apply -f k8s/spring-app-deployment.yaml
### kubectl apply -f k8s/spring-app-service.yaml

## Login to spring app pod

- kubectl exec -it spring-app -- /bin/sh
- curl http:/elastic:8080/es/health


## Uninstall helm
helm uninstall elastic




### Cleanup

kubectl delete secret es-ca es-http es-transport

kubectl delete pods elastic-0 elastic-1

kubectl delete statefulset elastic

kubectl delete service elastic


helm uninstall elastic


