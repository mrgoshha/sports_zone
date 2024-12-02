<#macro template auth=false admin=false>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SPORTSZone</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
              crossorigin="anonymous">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">SPORTSZone</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="/sports-events">Мероприятия</a>
                    <#if admin>
                        <a class="nav-link" href="/admin/sports-complex">Спорткомплексы</a>
                        <a class="nav-link" href="/admin">Пользователи</a>
                        <a class="nav-link" href="/admin/request">Запросы на регистрацию</a>
                        <a class="nav-link" href="/admin/query">Запросы SQL</a>
                    </#if>
                </div>
            </div>
            <#if auth || admin>
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-outline-primary">Выйти</button>
                </form>
            <#else>
                <button type="button" class="btn btn-outline-primary" onclick="window.location.href = '/login'">Войти
                </button>
            </#if>
        </div>
    </nav>

    <div class="container mt-4">
        <#nested/>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>