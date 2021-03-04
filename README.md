# Быстрый старт разработчика микросервисов. 
# Задание 5
	
Подключить в микросервис работающий со Spring Data JPA создание структуры БД с помощью liquibase. Скрипты должны создать структуру таблиц в СУБД Postgres.</br>

Ссылка на swagger:</br>
http://localhost:8088/</br>
http://localhost:8088/swagger-ui.html</br>
Контроллер:</br>
http://localhost:8088/swagger-ui.html#/test-message-controller</br>

Реализованные методы:</br>

1. Вызов метода получение списка сообщений:</br>
http://localhost:8088/test-message/api/v1

Результат:</br>
[{"id":2,"content":"test2"},{"id":3,"content":"test3"},{"id":4,"content":"test4"}]

2. Вызов метода получение сообщения по идентификатору:</br>
http://localhost:8088/test-message/api/v1/2

Результат:</br>
{"id":2,"content":"test2"}

3. Вызов метода создания сообщения</br>
http://localhost:8088/test-message/api/v1/</br>
{"content": "string"}</br>
Результат:</br>
1

4. Вызов метода изменения сообщения</br>

5. Вызов метода удаления сообщения</br>

Ссылки на использованную документацию:
https://docs.spring.io

## Build
mvn clean install
