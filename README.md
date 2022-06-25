# 

## requirements

- Java 1.8
- Spring Framework 5.0
- Javascript 1.8.5 с ECMAScript 6
- PostgreSQL 9.6.1
- jdbc 9.4

## docker

### database

build

`docker build -t kiarro/alien_db:latest .`

from database folder

run 

docker run -p 5432:5432 kiarro/alien_db

