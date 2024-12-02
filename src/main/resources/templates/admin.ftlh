<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="table-responsive scrollable" style="max-height: 500px;">
        <table class="table table-hover table-bordered text-center caption-top">
            <caption>Список пользователей</caption>
            <thead>
            <tr>
                <th>Email</th>
                <th>Активность</th>
                <th>Роли</th>
                <th>Бан</th>
                <th>Подробная информация</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <th>${user.email}</th>
                    <th><#if user.active>true<#else>false</#if></th>
                    <th><#list user.roles as role>${role}</#list></th>
                    <th>
                        <form action="/admin/user/ban/${user.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-outline-secondary btn-sm">Бан</button>
                        </form>
                    </th>
                    <th><a href="/user/${user.id}">Профиль</a></th>
                </tr>
            <#else>
                <tr>
                    <td colspan="5">Пользователей нет</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.template>

