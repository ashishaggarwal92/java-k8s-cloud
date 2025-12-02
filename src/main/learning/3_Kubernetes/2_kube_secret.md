# Kubernetes secret

## 1 - ca secret

kubectl create secret generic es-ca --from-file=ca.crt=ca/ca.crt

## transport certificate Single shared certificate
kubectl create secret generic es-transport --from-file=tls.crt=transport/es-transport.crt --from-file=tls.key=transport/es-transport.key --from-file=ca.crt=ca/ca.crt

## transport certificate per node certificate
###  - Node 0:

kubectl create secret generic es-node-0 --from-file=tls.crt=es-elasticsearch-0.crt --from-file=tls.key=es-elasticsearch-0.key

###  - Node 1:

kubectl create secret generic es-node-1 --from-file=tls.crt=es-elasticsearch-1.crt --from-file=tls.key=es-elasticsearch-1.key


## 4 - http - shared by all nodes 

kubectl create secret generic es-http --from-file=tls.crt=http/http.crt --from-file=tls.key=http/http.key

## check secrets

kubectl get secrets



