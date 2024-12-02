<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="card text-center">
        <div class="card-header">
            Подробная информация о пользователе ${user.name}
        </div>
        <div class="card-body">
            <p class="card-text">Email: ${user.email}</p>
        </div>
        <div class="card-body">
            <div class="card-header mb-2">
                 Мероприятия пользователя
            </div>
            <div class="list-group">
                <#list events as event>
                    <div class="list-group-item">
                        <a href="/sports-event/${event.id}" class="list-group-item list-group-item-action">${event.name}</a>
                    </div>
                <#else>
                    <p class="list-group-item">Пользователь пока не записан ни на одно мероприятние</p>
                </#list>
            </div>
        </div>
    </div>
</@t.template>


