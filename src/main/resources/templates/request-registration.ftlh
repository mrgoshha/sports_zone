<#import "blocks/main-template.ftlh" as t>
<#import "blocks/pageable.ftlh" as p>
<@t.template true true>
    <div class="list-group mb-3">
        <#list requests.getContent() as request>
            <div class="list-group-item">
                <a href="/admin/request/${request.id}"
                   class="list-group-item list-group-item-action">${request.user.email} ${request.event.name}</a>
            </div>
        <#else>
            <p class="list-group-item">заявок пока нет</p>
        </#list>
    </div>
    <@p.pageable "/admin/request?" requests/>
</@t.template>
