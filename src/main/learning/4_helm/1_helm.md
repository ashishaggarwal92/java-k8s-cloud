# HELM

helm version


# Create a Helm chart skeleton:
helm create es-cluster
cd es-cluster
rm -rf templates/*

# Add StatefulSet + Service YAML (templates/statefulset.yaml)

## Why we create stateful set
Purpose: StatefulSet manages stateful applications like Elasticsearch nodes, which need:

Stable network identities (DNS names don’t change)

Stable storage (persistent volumes per node)

Ordered startup and scaling

Why not a Deployment?

Deployment is for stateless apps (web servers, microservices).

If you redeploy, pods get new names and storage may be lost → bad for Elasticsearch, which requires each node to be uniquely identifiable and keep its data intact.

## Why we created service
We created a Service with clusterIP: None (headless service).

Purpose:

Service discovery between nodes:

Elasticsearch nodes need to discover each other to form a cluster.

Headless Service gives DNS names like elasticsearch-0.elasticsearch.default.svc.cluster.local and elasticsearch-1.elasticsearch.default.svc.cluster.local.

Networking:

Pods can communicate internally via stable DNS names, which is required for transport (9300) traffic.

Without this, nodes may fail to find each other.

# Create a Secret for Certificates

kubectl create secret generic es-certs \
--from-file=http.p12=~/es-certs/http.p12


# Deploy the Helm Chart

helm install es-cluster ./es-cluster


kubectl get pods
kubectl get svc


# Access Elasticsearch

kubectl port-forward svc/elasticsearch 9200:9200


curl -u elastic https://localhost:9200 --insecure


