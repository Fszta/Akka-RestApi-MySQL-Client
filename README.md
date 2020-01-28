# Akka-RestApi-MySQL-Client
<i>A basic akka restful Api with mysql client writing with JDBC.</i>

It contains a docker-compose file to deploy mysql database & adminer (a lightweight php database manager).
The repo illustrate how to build a basic Akka Rest API & write a mysql client to access data. For example it could be useful  for webapp to check user password store inside a mysql database.

## 1- Deploy MySQL database & Adminer 
Firt of all, we need to deploy our MySQL database, we'll do it with docker-compose:

### 1-1 MySQL container
We use port 3306 for both container & hostmachine

docker-compose.yml
```
version: '3.3'
services:
  db:
    image: mysql:latest
    container_name: mysql_db
    restart: always
    environment:
      MYSQL_USER: myuser
      MYSQL_PASSWORD: password
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: mydb
    volumes:
      - data:/var/lib/mysql
    ports:
      - 3306:3306
    expose:
      - 3306

```
### 1-2 Adminer container
Adminer is a database manager, phpmyadmin can be used too. We just need to add a few lines to our yaml file, this time we use port 8085 of the hostmachine, the user interface will be accessible at <a>http://ipHost:8085 </a>


```
  adminer:
    container_name: adminer
    image: adminer
    restart: always
    depends_on:
      - db
    ports:
      - 8085:8080
```
