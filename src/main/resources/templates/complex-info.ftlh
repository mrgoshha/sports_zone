<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
        <a role="button" href="/admin/sports-complex/update/${complex.id}"
           class="btn btn-outline-secondary btn-sm">Обновить</a>
        <form action="/admin/sports-complex/delete/${complex.id}" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-outline-secondary btn-sm">Удалить</button>
        </form>
    </div>

    <div class="card text-center">
        <div class="card-header">
            Подробная информация
        </div>
        <div class="card-body">
            <h5 class="card-title">${complex.name}</h5>
            <p class="card-text">Адрес спорткомплекса: ${complex.address}</p>
            <p class="card-text">Количество мест: ${complex.countSeats}</p>
        </div>
        <div class="card-body">
            <div class="card-header mb-2">Ближайшие мероприятия</div>
            <div class="list-group">
                <#list complex.sportsEvent as event>
                    <div class="list-group-item">
                        <a href="/sports-event/${event.id}" class="list-group-item list-group-item-action">${event.name}</a>
                    </div>
                <#else>
                    <p class="list-group-item">Мероприятий пока нет</p>
                </#list>
            </div>
        </div>
    </div>
</@t.template>

