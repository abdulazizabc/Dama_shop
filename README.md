# Dama_shop
---

## Содержание
1. [Краткое описание](#краткое-описание)
2. [Технологии и зависимости](#технологии-и-зависимости)
3. [Структура проекта](#структура-проекта)
4. [Сущности и модель данных (кратко)](#сущности-и-модель-данных-кратко)
5. [REST API — конечные точки (endpoints)](#rest-api-—-конечные-точки-endpoints)
6. [Запуск локально (шаги)](#запуск-локально-шаги)
7. [Конфигурация: application.yml / env vars](#конфигурация-applicationyml--env-vars)
8. [Инициализация БД (Postgres)](#инициализация-бд-postgres)
9. [Секреты и .gitignore — лучшие практики](#секреты-и-gitignore-—-лучшие-практики)
10. [Сборка в jar, запуск в prod и docker (рекомендации)](#сборка-в-jar-запуск-в-prod-и-docker-рекомендации)
11. [Частые ошибки и их решение](#частые-ошибки-и-их-решение)
12. [Тесты и CI](#тесты-и-ci)
13. [TODO / идеи для улучшения проекта](#todo--идеи-для-улучшения-проекта)

---

## Краткое описание
`Dama_shop` — учебный/пет-проект интернет-магазина на **Spring Boot** с использованием **Spring Data JPA**, **Spring Security (JWT)** и **PostgreSQL**.

Основной функционал:
- регистрация/логин (JWT)
- управление товарами (CRUD)
- корзина (cart)
- создание заказов, просмотр заказов
- роли пользователей (ROLE_USER / ROLE_ADMIN)

Проект подходит для практики backend-разработки, собеседований на junior/mid позиции и развёртывания в локальной среде.

---

## Технологии и зависимости
- Java 21 (в `pom.xml` указано `source/target = 21`)
- Spring Boot 3.5.0 (spring-boot-starter-parent)
- Spring Web, Spring Security, Spring Data JPA
- PostgreSQL driver
- Lombok
- jjwt (io.jsonwebtoken) — JWT utils
- Maven wrapper (`mvnw`)

---

## Структура проекта (основные пакеты)
```
src/main/java/com/example/dama_shop/
├─ controller/       # REST контроллеры (Auth, User, Product, Order, Cart, Admin ...)
├─ dto/              # DTO и мапперы
├─ model/            # JPA сущности и enum'ы (User, Product, Order, CartItem ...)
├─ repository/       # Spring Data JPA репозитории
├─ security/         # JWT сервис, фильтр и UserDetailsService
├─ service/          # Сервисы и их реализации
└─ DamaShopApplication.java
```

---

## Сущности и модель данных (кратко)
(см. модели в `src/main/java/com/example/dama_shop/model`)

- `User` — поля: id, username, email, password, age, role (enum `Role`), список заказов.
- `Product` — id, productName, productDescription, productPrice, productQuantity, category (enum `ProductCategory`).
- `CartItem` — привязан к пользователю/продукту, количество и т.п.
- `Order` / `OrderItem` — стандартная структура заказов, статус заказа (`OrderStatus` enum).

---

## REST API — конечные точки (основные)
_Здесь перечислены контроллеры и их маршруты (основано на изучении кода)._

### Auth
- `POST /auth/register` — регистрация нового пользователя. Возвращает `UserDTO`.
- `POST /auth/login` — логин, возвращает `{ token }` (`JwtResponse`).

### Users
- `GET /users/user/{id}` — получить профиль пользователя по id.
- `GET /users/by-username/{username}` — получить пользователя по username.
- `PUT /users/update-my-profile` — обновить профиль (требует аутентификации).

### Admin (только для admin)
- `GET /admin/all-users` — список всех пользователей.
- `POST /admin/save-user` — создать/сохранить пользователя.
- `POST /admin/update-user-info` — обновить информацию пользователя.
- `DELETE /admin/delete-user/{id}` — удалить пользователя.

### Products
- `GET /products/id/{id}` — получить товар по id.
- `GET /products/category/{category}` — получить товары по категории.
- `POST /products/save/product` — добавить товар (admin).
- `PUT /products/update/product/{id}` — обновить товар (admin).
- `DELETE /products/delete/product/{id}` — удалить товар (admin).

### Cart
- `POST /api/v1/carts` — добавить в корзину (тело запроса содержит productId/quantity и т.д.).
- `DELETE /api/v1/carts/{productId}` — удалить товар из корзины.
- `DELETE /api/v1/carts/clear` — очистить корзину.
- `GET /api/v1/carts` — получить содержимое корзины.

### Orders
- `POST /api/v1/orders` — создать заказ из корзины.
- `GET /api/v1/orders` — получить список заказов (для текущего пользователя / admin в зависимости от реализации).

> **Примеры curl**
>
> Регистрация:
> ```bash
> curl -X POST http://localhost:8080/auth/register \
>  -H "Content-Type: application/json" \
>  -d '{"username":"aziz","email":"a@a.com","password":"1234"}'
> ```
>
> Логин и получение токена:
> ```bash
> curl -X POST http://localhost:8080/auth/login \
>  -H "Content-Type: application/json" \
>  -d '{"username":"aziz","password":"1234"}'
> ```
>
> Дальше в защищённые эндпоинты добавляй: `-H "Authorization: Bearer <token>"`.

---

## Запуск локально (шаг за шагом)
### Пререквизиты
- Java 21 (OpenJDK 21) установлена и доступна через `java --version` и `javac --version`.
- Maven (`mvn`) или используй `./mvnw` из проекта.
- PostgreSQL запущен локально (или можно использовать Docker контейнер).

### Быстрый старт
1. Склонируй/распакуй проект.
2. Создай файл конфигурации `src/main/resources/application.yml` (ниже пример).
3. Создай базу данных в Postgres: `createdb dama_shop` (или другое имя, если менял url).
4. Запусти приложение:
   ```bash
   ./mvnw spring-boot:run
   # или
   mvn spring-boot:run
   ```

5. Открой `http://localhost:8080/` (или используй curl/Postman для API).

---

## Конфигурация: `application.yml` и переменные окружения

### Минимальный рабочий `application.yml` (локально)
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dama_shop
    username: postgres
    password: postgres_password_here
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: "REPLACE_WITH_SECURE_RANDOM_BASE64_OR_LONG_STRING"
  expiration: 3600000 # ms
```

> **Генерация безопасного ключа**
> ```bash
> # 32 байта base64
> openssl rand -base64 32
> ```

### Альтернатива: environment variables
В `application.yml` можно подставить переменные окружения:
```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASS}

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION:3600000}
```
Запускай приложение с экспортированными переменными:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/dama_shop
export DB_USER=postgres
export DB_PASS=secret
export JWT_SECRET=$(openssl rand -base64 32)
./mvnw spring-boot:run
```

---

## Инициализация БД (Postgres)
### Быстро (локально):
```bash
# создаём базу и пользователя (если нужно)
sudo -u postgres createuser -P dama_user
sudo -u postgres createdb -O dama_user dama_shop
```
При использовании `spring.jpa.hibernate.ddl-auto=update` таблицы создадутся автоматически при старте.

Если хочешь фиксированную миграцию — добавь Flyway или Liquibase и держи SQL-скрипты в `src/main/resources/db/migration`.

---

## Секреты и .gitignore — лучшие практики
- В репо хранить `application-example.yml` с пустыми/фейковыми значениями (пример структуры).
- Реальный `application.yml` и `.env` — в `.gitignore`.
- Для командной разработки: храните секреты в CI/CD/Secrets менеджере (GitHub Secrets, GitLab CI variables, Vault).
- Не храните пароли в открытом репо, даже если проект личный — через год можно забыть и случайно запушить.

Пример `application-example.yml` (в репо):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dama_shop
    username: postgres
    password: ""

jwt:
  secret: ""
  expiration: 3600000
```

---

## Сборка в jar, запуск и Docker (рекомендации)
### Сборка jar
```bash
./mvnw clean package
# или
mvn clean package
```
Запустишь jar:
```bash
java -jar target/dama_shop-0.0.1-SNAPSHOT.jar
```

### Пример Dockerfile (совет)
```Dockerfile
FROM eclipse-temurin:21-jdk-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```
Для production: не храните секреты в образе — пробрасывайте env vars.

---

## Частые ошибки и как их решать
- `Could not resolve placeholder 'jwt.secret'` — файл `application.yml` отсутствует или в нём нет `jwt.secret`. Решение: создать файл или добавить env-переменную `JWT_SECRET`.
- Проблемы с JDK: убедись, что Maven/IDE используют Java 21 (в `pom.xml` указан 21). Если IDE использует другую версию — сменить в настройках проекта.
- Ошибки JPA/DB: проверь `spring.datasource.url` и что Postgres запущен и доступен.

---

## Тесты и CI
В проекте есть базовые тесты (штамп). Для CI/Actions рекомендую:
- Запуск `mvn -DskipTests=false test` в pipeline
- Поднять ephemeral Postgres (docker) в CI для интеграционных тестов
- Держать `application-ci.yml` с конфигом для тестов

---

## TODO / идеи для улучшения
- Добавить Swagger / OpenAPI (ui) для удобной документации API
- Добавить интеграционные тесты с Testcontainers
- Добавить Docker Compose (app + postgres) для простого локального старта
- Хранение секретов через Vault/Kubernetes Secrets
- Добавить role-based access control более явно и тесты для security
- Миграции через Flyway

---

## Полезные команды (шпаргалка)
- `./mvnw spring-boot:run` — запуск
- `./mvnw clean package` — собрать jar
- `java -jar target/*.jar` — запустить собранный артефакт
- `psql -h localhost -U postgres -d dama_shop` — подключиться к БД
- `openssl rand -base64 32` — сгенерировать jwt.secret

