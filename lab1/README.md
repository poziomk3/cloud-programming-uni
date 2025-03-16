```sh
docker build -t lab1-app .

docker compose up -d

docker-compose logs --tail=0 --follow


docker-compose down
docker-compose up -d --build


docker-compose down --remove-orphans && docker-compose up -d --build && docker-compose logs --tail=0 --follow

```
