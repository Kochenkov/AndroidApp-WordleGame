# AndroidApp-WordleGame

## Информация для пользователей
Реализация [небезызвестной веб-игры](https://ru.wikipedia.org/wiki/Wordle) теперь доступна в качестве андроид приложения. Больше не нужно постоянное интернет соединение для того что бы отгадывать слова. Можете свободно играть в метро, либо за городом, скрашивая свой досуг и напрягая мозги:)

## Информация для разработчиков
Всем кому интересно, предлагаю присоединиться к разработке приложения. Просто берите открытый тикет из issue, уточняйте детали реализации, клонируйте проект, пишите код и открывайте пулл-реквест. На каждом ПР-е запускается билд, который собирает проект и прогоняет unit-тесты. После успешного прохождения ревью и влития кода в master ветку, ваше имя будет внесено в список разработчиков проекта.

Если вы занимаетесь тестированием, можете внести свой вклад в ручное тестирование проекта, либо написание автотестов. 

### Стек технологий 
* DI - Koin
* UI - Jetpac compose
* Asynchronous operations - Coroutines
* Многомодульная архитектура на базе clean anchitecture, состоящая из gradle модулей:
  * application (android)
    * presentation
    * di
  * domain (only kotlin, full unit test coverage)
  * data (android)

### В настоящий момент, приложение находится в разработке

<img src="https://user-images.githubusercontent.com/38836366/159764991-d625f0b4-f797-4c85-a254-e2a23f20a0f7.jpg" width="200" >
