# Компиляция и запуск

Для успешного запуска проекта выполните следующие команды:

### Генерация `.jar` файла

```bash
mvn clean package
```

- **clean** — удаляет старые зависимости и `.jar` файлы.
- **package** — создает `.jar` файл, который будет находиться в автоматически сгенерированной папке `target`.

### Копирование файла `.env`

Необходимо скопировать файл `.env` в папку `target`:

```bash
cp *РАСПОЛОЖЕНИЕ .env ФАЙЛА*/.env *НЫНЕШНЯЯ ДИРЕКТОРИЯ*/target
```

### Запуск `.jar` файла

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

## .env

- `app_name` — Идентификатор приложения.
- `server_port` — Порт сервера.
- `postgres_host` — Хост базы данных PostgreSQL.
- `postgres_port` — Порт базы данных PostgreSQL.
- `postgres_db` — Имя базы данных PostgreSQL.
- `postgres_params` — Параметры подключения к базе данных.
- `postgres_user` — Имя пользователя базы данных PostgreSQL.
- `postgres_password` — Пароль пользователя базы данных PostgreSQL.
- `postgres_max_pool_size` — Максимальный размер, который может достичь пул соединений.
- `postgres_timeout` — Максимальное количество миллисекунд, в течение которых клиент будет ожидать подключения к PostgreSQL.
- `postgres_min_idle` — Минимальное количество незанятых соединений, которое HikariCP пытается поддерживать в пуле.

### Пример `.env` файла
```env
app_name=TestApp
server_port=8080
postgres_host=localhost
postgres_port=5432
postgres_db=test_db
postgres_params=sslmode=disable
postgres_user=test_user
postgres_password=test_password
postgres_max_pool_size=20
postgres_timeout=30000
postgres_min_idle=5
```

