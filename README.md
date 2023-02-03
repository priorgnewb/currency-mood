## Стек технологий Java 17, Spring Boot, Spring Cloud OpenFeign, Thymeleaf
## Техническое задание
#### Создать сервис, который обращается к сервису курсов валют и отображает gif:  
• если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich  
• если ниже - отсюда https://giphy.com/search/broke 

#### Ссылки
• REST API курсов валют - https://docs.openexchangerates.org/  
• REST АРI гифок - https://developers.giphy.com/docs/api#quick-start-guide

#### Must have
• Сервис на Spring Boot 2 + Java/Kotlin  
• Запросы приходят на НТТР (должен быть написан в соответствии с REST conventions), туда передается код валюты по отношению с которой сравнивается USD  
• Для взаимодействия с внешними сервисами используется Feign  
• Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки  
• На сервис написаны тесты (для мока внешних сервисов можно использовать @MockBean или WireMock

#### Nice to Have
• Сборка и запуск Docker контейнера с этим сервисом

## Инструкция по запуску в Intellij IDEA
1. File -> New -> Project from Version control -> https://github.com/priorgnewb/currency-mood.git  
2. Для работы сервиса нужно зарегистрироваться в двух сторонних сервисах и получить API-токены  
2.1 https://openexchangerates.org/signup/free    получаем AppId  
2.2 https://giphy.com/login   после регистрации добавляем в профиле App https://developers.giphy.com/dashboard/?  
Будет сгенерирован API Key  
2.3 Вставляем полученные токены в файл application.properties  
3. Run -> Run -> CurrencyMoodApplication  

Приложение доступно по адресу http://localhost:8086/api/v1/currency-mood/  

По умолчанию с USD сравнивается валюта RUB, для другой валюты нужно добавить её трехзначный код в URL, например:  
http://localhost:8086/api/v1/currency-mood/GBP  

![img1](https://github.com/priorgnewb/currency-mood/blob/master/rich.png)
