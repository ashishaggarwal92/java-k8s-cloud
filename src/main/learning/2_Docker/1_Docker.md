# Docker

## To create docker image
docker build -t myapp .

## check images

docker images

## run
docker run -d -p 8081:8081 --name myapp-container myapp:latest

## Check logs
docker logs -f myapp-container

## Restart container
docker restart myapp-container
 
## Stop container
docker stop myapp-container

## Remove container
docker rm -f myapp-container

## check containers
docker ps



