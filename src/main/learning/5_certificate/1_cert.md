# CERT

## If we want to enable HTTP + TLS that is recommended for PROD grade app

### We need below certificates

1) ca cert
2) http certificate for elastic search http layer
3) transport cert for ES node-to-node 
4) Client cert Spring boot to ES

#### A. Certificate Authority (CA)

ca.crt

ca.key (never put inside Kubernetes)

#### B. Node certificates (per ES pod)

For 2 nodes:

es-elasticsearch-0.crt
es-elasticsearch-0.key
es-elasticsearch-1.crt
es-elasticsearch-1.key

#### C. HTTP certificate (shared by all nodes)

Recommended: Create one wildcard cert for the service name:

http.crt
http.key


#### SANs:

DNS: es-elasticsearch
DNS: es-elasticsearch.default.svc
DNS: *.es-elasticsearch.default.svc



## Create cert

1) Open power-shell
2) goto cd D:\Study\elasticsearch-8.17.3
3) .\bin\elasticsearch-certutil ca --pem
4) extract ca folder and create a ca folder in D:\Study\elasticsearch-8.17.3. copy both files in it
5) create an instances.yml file
6) Generate transport cert
6) run command .\bin\elasticsearch-certutil cert --pem --in .\instances.yml --ca-cert .\ca\ca.crt --ca-key .\ca\ca.key
7) Generate http cert
8) .\bin\elasticsearch-certutil cert --pem --in .\http.yml --ca-cert .\ca\ca.crt --ca-key .\ca\ca.key

All certs are ready
