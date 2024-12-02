<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
        <#if !errorMessage?? && !approveMessage??>
            <form action="/admin/request/approve/${request.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-outline-secondary btn-sm">Одобрить</button>
            </form>
        </#if>
        <form action="/admin/request/delete/${request.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-outline-secondary btn-sm">Удалить</button>
        </form>
    </div>
    <div class="card text-center mb-2">
        <div class="card-header">
            Подробная информация
        </div>
        <div class="card-body">
            <p class="card-text">Пользователь: ${request.user.email}</p>
            <p class="card-text">Мероприятие: ${request.event.name}</p>
            <p class="card-text">Дата подачи заявки: ${request.dateOfCreated}</p>
            <p class="card-text">Статус заявки: <#if request.status>
                    True
                <#else>
                    False
                </#if></p>
        </div>
    </div>
    <#if errorMessage??>
        <div class="alert alert-danger text-center" role="alert">
            <h5 class="alert-heading">${errorMessage}</h5>
        </div>
    <#elseif approveMessage??>
        <div class="alert alert-success text-center" role="alert">
            <h5 class="alert-heading">${approveMessage}</h5>
        </div>
    </#if>
</@t.template>


