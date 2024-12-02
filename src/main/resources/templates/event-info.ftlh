<#import "blocks/main-template.ftlh" as t>
<@t.template auth admin>
    <#if admin?? && admin>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
            <a role="button" href="/admin/sports-event/update/${event.id}" class="btn btn-outline-secondary btn-sm">Обновить</a>
            <form action="/admin/sports-event/delete/${event.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="btn btn-outline-secondary btn-sm">Удалить</button>
            </form>
        </div>
    </#if>
    <div class="card text-center">
        <div class="card-header">
            Подробная информация
        </div>
        <div class="card-body">
            <h5 class="card-title">${event.name}</h5>
            <p class="card-text">Спорткомплекс: ${event.sportsComplex.name}</p>
            <p class="card-text">Дата и время начала: ${event.startDateAndTime}</p>
            <p class="card-text">Длительность(в часах): ${event.numberOfHours}</p>
            <p class="card-text">Тренер: ${event.trainer.name}</p>
            <p class="card-text">Стоимость участия: ${event.price}</p>
            <p class="card-text">Кол-во свободных мест: ${event.sportsComplex.countSeats - event.countSeats}
                из ${event.sportsComplex.countSeats}</p>
        </div>
        <div class="card-footer text-muted">
            <#if auth??>
                <form action="/registration-event/${event.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-primary">Зарегестрироваться</button>
                </form>
            <#else>
                Нужно войти чтобы зарегестрироваться на мероприятие
            </#if>
        </div>
        <#if admin?? && admin>
            <div class="card-body">
                <h5 class="card-title">Посетители мероприятия</h5>
                <div class="list-group">
                    <#list event.visitors as visitor>
                        <div class="list-group-item">
                            <a href="/user/${visitor.id}" class="list-group-item list-group-item-action">${visitor.email}</a>
                        </div>
                    <#else>
                        <p class="list-group-item">Посетителей пока нет </p>
                    </#list>
                </div>
            </div>
        </#if>
    </div>
</@t.template>

