# Commands:

1. ```sh
docker compose up
``
2.  ```sh
docker compose up --scale user-service=2 --scale user-man-service=3 --scale stats-service=4 --scale role-service=1
```
3. ```sh 
docker compose up --scale stats-service=5 -d
```
4. ```sh
docker compose down
```




5. ```sh 
docker service scale mystack_stats-service=6
```

6. ```sh
docker stack rm mystack
```
7. ```sh
docker stack deploy -c docker-compose.yml mystack
```