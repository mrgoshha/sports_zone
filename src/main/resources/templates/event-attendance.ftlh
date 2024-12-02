<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <form method="get" action="/admin/sports-complex/attendance">
        <div class="row mx-auto text-center mb-3">
            <div class="col-sm">
                <select name="sportsComplex" class="form-select">
                    <option value="">Выберите спорткомплекс</option>
                    <#list complexesAll as complex>
                        <option value="${complex.id}">${complex.name}</option>
                    </#list>
                </select>
            </div>
            <div class="col-sm">
                <input type="datetime-local" name="startDateAndTime" class="form-control"
                       placeholder="Стоимость участия">
            </div>
            <div class="col-sm-3">
                <button style="width: 90%" type="submit" class="btn btn-primary">Поиск</button>
            </div>
        </div>
    </form>
    <div class="table-responsive scrollable" style="max-height: 500px;">
        <table class="table table-hover table-bordered text-center caption-top">
            <caption>Отчет посещаемости площадок</caption>
            <thead>
            <tr>
                <th>Спорткомплекс</th>
                <th>Количество посетителей</th>
            </tr>
            </thead>
            <tbody>
            <#list complexes?keys as name>
                <tr>
                    <td>${name}</td>
                    <td>${complexes[name]}</td>
                </tr>
            <#else>
                <tr>
                    <td colspan="2">Спорткомплексов пока нет</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@t.template>

