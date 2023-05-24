# orm-playground

Project for comparison and trying out different ORMs

## database
```
docker run --name orm-postgres -p 5432:5432 -e POSTGRES_PASSWORD=secret -d postgres:15
```
load scripts from schema.sql and data.sql manually

## jooq regeneration

```
./gradlew generateOrmPlaygroundJooq
```