# Running Elastic 2 node cluster on Rancher using TLS + HTTP + HELM + Secret


## Step 1 - Follow cert creation  - 1_cert.md

## Step 2 - Follow kubernetes secret creation for certs - 3_Kubernetes/2_kube_secret.md

## Step 3 - Setup Files in elastic-tls folder

## run command to install helm

helm install elastic ./elastic-tls

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

## 2- port forwad for local intellij + postman

kubectl port-forward svc/elastic 9200:9200

## 3- ignore host name verifier in restclient

# test using java app docker image on cluster

## clean inatall
## docker build -t my-spring-app:latest .
## kubectl apply -f k8s/spring-app-deployment.yaml
## kubectl apply -f k8s/spring-app-service.yaml

## Uninstall helm
helm uninstall elastic




### Cleanup

kubectl delete secret es-ca es-http es-transport

kubectl delete pods elastic-0 elastic-1

kubectl delete statefulset elastic

kubectl delete service elastic


helm uninstall elastic


