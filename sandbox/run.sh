# Criar as docker networks
docker network create elastic

#Criar os docker volumes
docker volume create es01

docker compose -f elk/docker-compose.yml up -d