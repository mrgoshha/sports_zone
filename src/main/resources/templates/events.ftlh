<#import "blocks/main-template.ftlh" as t>
<#import "blocks/pageable.ftlh" as p>
<@t.template auth admin>
    <#assign nameParam = "" />
    <#assign complexParam = "" />
    <#assign trainerParam = "" />
    <#assign priceParam = "" />
    <#if name??>
        <#assign nameParam = name />
    </#if>
    <#if complex??>
        <#assign complexParam = complex.id />
    </#if>
    <#if trainer??>
        <#assign trainerParam = trainer.id />
    </#if>
    <#if price??>
        <#assign priceParam = price />
    </#if>
    <form method="get" action="/sports-events">
        <div class="row mx-auto text-center mb-3">
            <div class="col-sm-3">
                <input type="text" name="name" <#if name??>value="${name}"</#if>
                       class="form-control" placeholder="Название мероприятия">
            </div>
            <div class="col-sm-3">
                <select name="sportsComplex" class="form-select">
                    <option value="">Выберите спорткомплекс</option>
                    <#list complexes as complex>
                        <option value="${complex.id}">${complex.name}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm">
                <select name="trainer" class="form-select">
                    <option value="" selected disabled>Выберите тренера</option>
                    <#list trainers as trainer>
                        <option value="${trainer.id}">${trainer.name}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm">
                <input type="number" name="price" <#if price??>value="${price}"</#if>
                       class="form-control" placeholder="Стоимость участия">
            </div>
            <div class="col-sm">
                <button style="width: 90%" type="submit" class="btn btn-primary">Поиск</button>
            </div>
        </div>
    </form>
    <#if admin?? && admin>
        <div class="text-center mb-3">
            <button style="width: 100%" type="button" class="btn btn-outline-success"
                    onclick="window.location.href = '/admin/sports-event/create'">Добавить новое мероприятие
            </button>
        </div>
    </#if>
    <div class="list-group mb-3">
        <#list events.getContent() as event>
            <div class="list-group-item">
                <a href="/sports-event/${event.id}" class="list-group-item list-group-item-action">${event.name}</a>
            </div>
        <#else>
            <p class="list-group-item">Мероприятий пока нет</p>
        </#list>
    </div>

    <@p.pageable "/sports-events?name=${nameParam}&complex=${complexParam}&trainer=${trainerParam}&price=${priceParam}&" events/>
</@t.template>


