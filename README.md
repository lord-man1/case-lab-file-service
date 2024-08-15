# Запуск с помощью `docker/docker-compose`

Для успешного запуска проекта выполните следующие команды:

### Удалить сгенерированные/скомпилированные файлы

```bash
mvn clean
```

- **clean** — удаляет старые зависимости и `.jar` файлы.

### Собрать `docker image` приложения (подробнее в `./docker/README.md`).
Выполните команду для сборки образа приложения:

```commandline
buildah bud --no-cache -f docker/Dockerfile -t case-lab/file-service:local .
```

ИЛИ

```commandline
docker build --no-cache -f docker/Dockerfile -t case-lab/file-service:local .
```

### Запустить `docker-compose` файл

Перейдите в папку `docker`:

```bash
cd docker/
```

Далее выполните команду для запуска `docker-compose` файла:

```bash
docker compose up
```

# Запуск с помощью `.jar` файла
### Запустить `PostgreSQL` ([с помощью `docker`](https://habr.com/ru/articles/578744/) или [локально](https://www.baeldung.com/linux/postgresql-start))

Настройки сервиса по умолчанию для подключения к БД (подробнее `src/main/resources/application.yml`):

| Параметр                 | Значение        |
|--------------------------|-----------------|
| Хост                     | localhost       |
| Порт                     | 5432            |
| Имя БД                   | test_db         |
| Имя пользователя БД      | test_user       |
| Пароль пользователя БД   | test_password   |

### Сгенерировать `.jar` файл

```bash
mvn clean package
```

- **clean** — удаляет старые зависимости и `.jar` файлы.
- **package** — создает `.jar` файл, который будет находиться в автоматически сгенерированной папке `target`.

Перейдите в папку `target`:

```bash
cd target/
```

Далее выполните команду для запуска `.jar` файла:

```bash
java -jar FileService-*.jar
```

Где:

- **`FileService`** — первая часть имени `.jar` файла, формируется из `<artifactId>` в `pom.xml`.
- **`*`** — версия, формируемая из `<version>` в `pom.xml`.

Например, для версии `0.0.1`, команда будет выглядеть так:

```bash
java -jar FileService-0.0.1.jar
```

Тут же должны задаваться параметры для JVM и другие настройки при запуске.

По умолчанию будут применены настройки из `src/main/resources/application.yml`.


# .env

- `SERVICE_NAME` — Идентификатор приложения.
- `SERVER_PORT` — Порт сервера.
- `POSTGRES_HOST` — Хост базы данных PostgreSQL.
- `POSTGRES_PORT` — Порт базы данных PostgreSQL.
- `POSTGRES_DB` — Имя базы данных PostgreSQL.
- `POSTGRES_USER` — Имя пользователя базы данных PostgreSQL.
- `POSTGRES_PASSWORD` — Пароль пользователя базы данных PostgreSQL.
- `POSTGRES_MAX_POOL_SIZE` — Максимальный размер, который может достичь пул соединений.
- `POSTGRES_TIMEOUT` — Максимальное количество миллисекунд, в течение которых клиент будет ожидать подключения к
  PostgreSQL.
- `POSTGRES_MIN_IDLE` — Минимальное количество незанятых соединений, которое HikariCP пытается поддерживать в пуле.

### Пример `.env` файла

```env
SERVICE_NAME=file-service
SERVER_PORT=8080
POSTGRES_HOST=postgres
POSTGRES_PORT=5432
POSTGRES_DB=test_db
POSTGRES_USER=test_user
POSTGRES_PASSWORD=test_password
POSTGRES_MAX_POOL_SIZE=20
POSTGRES_TIMEOUT=30000
POSTGRES_MIN_IDLE=5
```