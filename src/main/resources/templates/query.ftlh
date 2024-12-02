<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <form action="/admin/query" method="get">
        <div class="mb-3">
            <label for="exampleFormControlTextarea" class="form-label">Введите запрос</label>
            <textarea name="sql" class="form-control" id="exampleFormControlTextarea" rows="3"><#if sql??>${sql}</#if></textarea>
        </div>
        <div class="mb-3">
            <button style="width: 100%" type="submit" class="btn btn-primary">Запустить</button>
        </div>
    </form>
    <div class="list-group mb-3">
        <#if request??>
            <#list request as map>
                <li class="list-group-item">
                <#list map as key, value>
                    ${key}: ${value}
                </#list>
                </li>
            <#else>
                <li class="list-group-item">По вашему запросу ничего не найдено</li>
            </#list>
        </#if>
    </div>
    <#if exception??>
        <div class="alert alert-danger text-center" role="alert">
            <h5 class="alert-heading">${exception.message}</h5>
        </div>
    </#if>
</@t.template>