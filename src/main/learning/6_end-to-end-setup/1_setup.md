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





##Uninstall helm
helm uninstall elastic

# Connect cluster using 9200

1) kubectl exec -it elastic-0 -- curl -k https://localhost:9200
   Check if security is enabled . This will give error


