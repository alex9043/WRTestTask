﻿# WRSimpleSubscription

Простой REST API микросервис для управления пользователями и подписками

## Техническое задание

Необходимо разработать микросервис на Spring Boot 3, который будет
предоставлять REST API для управления пользователями и их подписками на
сервисы.

Проект должен использовать Java 17

## Функциональные требования

1. API для управления пользователями
    - Создание пользователя.
    - Получение информации о пользователе.
    - Обновление данных пользователя.
    - Удаление пользователя.
2. API для подписок
    - Добавление подписки пользователю.
    - Получение списка подписок пользователя.
    - Удаление подписки.
    - Подписки представляют собой подписки на цифровые сервисы, такие как
      YouTube Premium, VK Музыка, Яндекс.Плюс, Netflix и другие стриминговые
      платформы.
3. Интеграция с базой данных
    - Использовать PostgreSQL.
    - Таблицы: users, subscriptions.
4. Логирование
    - Логирование через SLF4J.
5. Docker
    - Создать Dockerfile для развертывания сервиса.
    - Разработать docker-compose.yml, который позволит локально запускать проект
      вместе с базой данных.

## Требования к API

### User endpoints:

**POST /users** - создать пользователя

**GET /users/{id}** - получить информацию о пользователе

**PUT /users/{id}** - обновить пользователя

**DELETE /users/{id}** - удалить пользователя

### Subscriptions endpoints:

**POST /users/{id}/subscriptions** - добавить подписку

**GET /users/{id}/subscriptions** - получить подписки пользователя

**DELETE /users/{id}/subscriptions/{sub_id}** - удалить подписку

**GET /subscriptions/top** - получить ТОП-3 популярных подписок

## Реализация

### Запуск

Создание секрета (пароля) для postgres
```bash
mkdir db
echo "password" > db/password.txt   
```
ИЛИ создать файл password.txt в директории ./db/ и вписать свой пароль

Запуск docker-compose
```bash
docker compose up --build
```

### Возможности

Сервис может создавать пользователей, получать их по id, изменять или удалять.
Создавать подписки для пользователей, получать подписки пользователей, удалять их и получать топ 3 подписки.

#### Пример для пользователей

- **Request** `POST localhost:8080/users`

  **Body**
   ```json
   {
      "id": "123",
      "username": "test",
      "email": "email@email.email"
   }
   ```

  **Response**  `201 Created`
   ```json
    {
      "id": "123",
      "username": "test",
      "email": "email@email.email"
    }
   ```

- **Request** `GET localhost:8080/users/123`

  **Response** `200 Ok`
   ```json
    {
      "id": "123",
      "username": "test",
      "email": "email@email.email"
    }
   ```

- **Request** `PUT localhost:8080/users/123`

  **Body**
   ```json
    {
      "username": "test2"
    }
   ```

  **Response**  `201 Created`
   ```json
    {
      "id": "123",
      "username": "test",
      "email": "email@email.email"
    }
   ```
- **Request** `DELETE localhost:8080/users/123`
  **Response** `204 No Content`

#### Пример для подписок

- **Request** `POST localhost:8080/users/123/subscriptions`

  **Body**
   ```json
    {
      "service_type": "netflix"
    }
   ```

  **Response**  `201 Created`
   ```json
    {
      "service_type": "NETFLIX",
      "user_id": "123"
    }
   ```

- **Request** `GET localhost:8080/users/123/subscriptions`

  **Response**  `201 Ok`
   ```json
    {
    "users_subscriptions": [
        {
            "id": "bb37c80d-c984-4f4c-8f31-e98ff9199c92",
            "services_type": "YOUTUBE_PREMIUM",
            "user_id": "123"
        },
        {
            "id": "d11a3e32-3db4-4e8a-8e39-bb6af2f59ddc",
            "services_type": "VK_MUSIC",
            "user_id": "123"
        },
        {
            "id": "857dc10f-930b-467a-8ab6-48046146c30d",
            "services_type": "YANDEX_PLUS",
            "user_id": "123"
        },
        {
            "id": "2e07a30a-a003-42c7-9c3d-8f292fe9f48b",
            "services_type": "NETFLIX",
            "user_id": "123"
        }
    ]
    }
   ```

- **Request** `localhost:8080/users/123/subscriptions/26ffdc25-123c-48ff-8dee-51e08d867021`
  **Response** `204 No Content`

- **Request** `GET localhost:8080/subscriptions/top`

  **Response**  `200 OK`
   ```json
    {
    "top_subscriptions": [
        {
            "services_type": "VK_MUSIC"
        },
        {
            "services_type": "YOUTUBE_PREMIUM"
        },
        {
            "services_type": "YANDEX_PLUS"
        }
    ]
  }
   ```

#### OpenApi

Можно получить OpenAPI документацию для сервиса. Либо в браузере, по адресу `/swagger-ui/index.html`,
либо запросом `/v3/api-docs`
