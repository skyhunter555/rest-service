# Быстрый старт разработчика микросервисов. Задание 1
Реализация: Building a RESTful Web Service 
В первой части примера рассмотрен процесс создания web-службы RESTful «Hello, World» с помощью Spring.
В второй части примера добавлен AOP логирование с помощью Spring.

1. Вызов метода без параметра:
http://localhost:8088/greeting

Результат:
{"id":1,"content":"Hello, World!"}

2. Вызов метода с параметром:
http://localhost:8088/greeting?name=User

Результат:
{"id":2,"content":"Hello, User!"}

Лог:
LogExecutionTimeAspect - =======> Выполнение метода: greeting
LogExecutionTimeAspect - Параметры метода: [User]
GreetingController - Request greeting name: User
LogExecutionTimeAspect - Greeting com.example.restservice.GreetingController.greeting(String) выполнен за: 15 мс
LogExecutionTimeAspect - <======= Результат: Greeting(id=1, content=Hello, User!)

Ссылки на использованную документацию:
https://spring.io/guides/gs/rest-service/

https://sysout.ru/vvedenie-v-aop-v-spring-boot/

## Build
mvn clean install
