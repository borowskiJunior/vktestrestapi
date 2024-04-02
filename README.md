# REST API на Java Spring Boot + Spring Security и Spring Cache.
## Описание:

Этот проект представляет собой REST API, построенный на Java Spring Boot, который перенаправляет запросы к [JSON Placeholder](https://jsonplaceholder.typicode.com/) (/post, /album, /user) и возвращает ответы.
## Функциональные возможности:

* Перенаправление запросов GET, POST, PUT, PATCH, DELETE к API JSONPlaceholder
* Авторизация и аутентификация пользователей с помощью Spring Security
* Поддержка ролей: "admin", "posts", "users", "albums"
* Кэширование ответов с помощью Spring Cache

## API:

* GET /api/posts - Получить список постов
* GET /api/posts/{id} - Получить пост по ID
* POST /api/posts - Создать пост
* PUT /api/posts/{id} - Обновить пост
* PATCH /api/posts/{id} - Частично обновить пост
* DELETE /api/posts/{id} - Удалить пост
  
Аналогичные API endpoints доступны для /api/albums и /api/users.

## Роли:

* admin: полный доступ ко всем API 
* posts: доступ к API постов
* users: доступ к API пользователей
* albums: доступ к API альбомов

Параметры входа admin (остальные аналогичны):
* Логин: "admin"
* Пароль: "admin"
  
## Кэширование:

Ответы (по запросу get) API кэшируются с помощью Spring Cache, что позволяет уменьшить количество обращений с стороннему api и увеличить производительность.
