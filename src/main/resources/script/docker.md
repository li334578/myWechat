## mysql

```docker
docker run --name myMySQL -p 3306:3306 -d mysql -e MYSQL_ROOT_PASSWORD=123456
docker run --name myRedis -p 6379:6379 -d redis --requirepass "root"
```