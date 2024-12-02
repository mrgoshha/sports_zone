<#import "blocks/main-template.ftlh" as t>
<#import "blocks/pageable.ftlh" as p>
<@t.template true true>
    <div class="text-center mb-2">
        <button style="width: 100%" type="button" class="btn btn-outline-success"
                onclick="window.location.href = '/admin/sports-complex/create'">Добавить новый спорткомплекс
        </button>
    </div>
    <div class="text-center mb-3">
        <a role="button" style="width: 100%" href="/admin/sports-complex/attendance"
           class="btn btn-outline-secondary">Отчет посещаемости площадок</a>
    </div>
    <div class="list-group mb-3">
        <#list complexes.getContent() as complex>
            <div class="list-group-item">
                <a href="/admin/sports-complex/${complex.id}" class="list-group-item list-group-item-action">${complex.name}</a>
            </div>
        <#else>
            <p class="list-group-item">спорткомплексов пока нет</p>
        </#list>
    </div>
    <@p.pageable "/admin/sports-complex?" complexes/>
</@t.template>



