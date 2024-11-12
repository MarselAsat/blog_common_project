# Блог-платформа (Микросервисная архитектура)

## Описание проекта
Распределенное приложение для ведения блога с микросервисной архитектурой. Позволяет пользователям создавать посты, комментировать их, использовать теги для категоризации контента.

## Основной функционал
- Создание, чтение, обновление и удаление постов
- Система комментариев
- Тегирование постов
- Аутентификация и авторизация пользователей
- Кэширование данных
- Балансировка нагрузки
- Сервис обнаружения (Service Discovery)
- API Gateway для маршрутизации запросов

## Архитектура
Проект состоит из следующих микросервисов:
1. **Blog Service** - основной сервис для работы с постами и комментариями
2. **User Service** - сервис управления пользователями
3. **API Gateway** - единая точка входа в приложение
4. **Service Discovery** - сервис обнаружения на базе Eureka
5. **Common Library** - общая библиотека для переиспользуемого кода

## Технологический стек
### Backend
- Java 17
- Spring Boot 3.3.1
- Spring Cloud
- Spring Data JPA
- Spring Security
- Spring Cloud Gateway
- Netflix Eureka

### База данных и кэширование
- PostgreSQL
- Redis
- Liquibase (миграции)

### Авторизация
- Keycloak

### Инструменты сборки и развертывания
- Maven
- Docker
- Docker Compose

## Запуск проекта
1. Убедитесь, что у вас установлены Docker и Docker Compose
2. Склонируйте репозиторий
3. Выполните сборку проекта:
  bash
  mvn clean package
4. Запустите приложение через Docker Compose:
   bash
  docker-compose up -d


## Порты сервисов
- API Gateway: 9000
- Eureka Server: 9999
- Keycloak: 8900
- PostgreSQL: 5442
- Redis: 6379

## Особенности реализации
- Использование JWT токенов для аутентификации
- Кэширование данных в Redis
- Автоматическая регистрация сервисов через Eureka
- Балансировка нагрузки между инстансами сервисов
- Liquibase для управления схемой базы данных
- Docker контейнеризация всех компонентов системы