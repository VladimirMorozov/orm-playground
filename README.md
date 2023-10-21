# orm-playground

Project for comparison and trying out different ORMs

## database
```shell
docker run --name orm-postgres -p 5432:5432 -e POSTGRES_PASSWORD=secret -d postgres:15
```
```shell
docker start orm-postgres
```
load scripts from schema.sql and data.sql manually

## jooq regeneration

```
./gradlew generateOrmPlaygroundJooq
```