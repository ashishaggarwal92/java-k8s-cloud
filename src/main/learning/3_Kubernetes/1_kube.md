# Kubernetes

1) Open Rancher desktop
2) Enable Kubernetes
3) Run Below commands:

kubectl version

kubectl cluster-info

kubectl get nodes

kubectl get pods -n kube-system

# Create a test deployment

kubectl create deployment nginx-test --image=nginx

kubectl get pods

# Scale a deployment

kubectl scale deployment nginx-test --replicas=2

# Stop pods

kubectl scale deployment nginx-test --replicas=0    

# Get deployment

kubectl get deployment

# Delete Deployment

kubectl delete deployment nginx-test


# secrets
kubectl get secrets



