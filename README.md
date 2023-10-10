## Docker for mysql

### Start container
```bash
docker run -p 3308:3306 --name mysql_8.0.34 -e "MYSQL_ROOT_PASSWORD=root" -d mysql:8.0.34
```

```bash
docker exec -it mysql_8.0.34 /bin/bash
```

```bash
mysql -u root -p -A
```

```bash
docker ps -a
```