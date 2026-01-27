
# Проект по автоматизации тестирования API на базе сервиса [ReqRes](https://reqres.in/)

> ReqRes - это публичный тестовый API‑сервис (mock‑сервер), предназначенный для разработки и тестирования веб‑ и мобильных приложений.
> 
## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure-testops">Интеграция с Allure TestOps</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>
____
<a id="tools"></a>
## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="ht[images](images)tps://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="images/logo/Allure2.svg" width="50" height="50"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://rest-assured.io//"><img src="images/logo/RestAssured.png" width="50" height="50"  alt="RestAssured"/></a>  
</p>

____
<a id="cases"></a>
## <a name="Список автоматизированных тест-кейсов">**Список автоматизированных тест-кейсов:**</a>
____
- ✓ *Проверка успешного создания нового пользователя*
- ✓ *Проверка успешной регистрации нового пользователя*
- ✓ *Проверка возникновения ошибки при регистрации нового пользователя без пароля*
- ✓ *Проверка получения списка пользователей по номеру страницы*
- ✓ *Проверка получения информации о конкретном пользователе*
- ✓ *Проверка возникновения ошибки, если пользователь не найден*
- ✓ *Проверка удаления существующего пользователя*

____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/038-L_Surkov-JenkinsQAGuru_API_Tests/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/038-L_Surkov-JenkinsQAGuru_API_Tests/"><img src="images/screen/jenkins_build.png" alt="Jenkins" width="950"/></a>  
</p>


### **Параметры сборки в Jenkins:**

- *ENVIRONMENT (тестовая среда, по умолчанию Test)*
- *API_KEY (уникальный токен из ReqRes)*

<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean executeApiTests
-Dapi.key="токен"
```

***Удалённый запуск через Jenkins:***
```bash  
clean executeApiTests
-Dapi.key="${API_KEY}"
```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/038-L_Surkov-JenkinsQAGuru_API_Tests/4/allure/)</a>
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/jenkins.png" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screen/jenkins_tests.png" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="images/screen/allure_graths.png" width="850">
</p>

____

<a id="allure-testops"></a>
## <img alt="TestOps" height="25" src="images/logo/TestOps_logo_2.png" width="25"/></a> <a name="TestOps"></a>Интеграция с Allure TestOps [проект](https://allure.autotests.cloud/launch/51075/tree/760195?search=W3siaWQiOiJzdGF0dXMiLCJ0eXBlIjoidGVzdFN0YXR1c0FycmF5IiwidmFsdWUiOlsicGFzc2VkIl19XQ%3D%3D&treeId=0)</a>
___
<p align="center">  
<img title="Allure TestOps Overview Dashboard" src="images/screen/testops.png" width="850">  
</p>


____
<a id="telegram"></a>
## <img alt="Allure" height="25" src="images/logo/Telegram.svg" width="25"/></a> Уведомление в Telegram при помощи бота
____
<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/telegram.png" width="550">  
</p>


