<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="col-md-6 mx-auto">
        <form action="/admin/sports-event/update/${event.id}" method="post">
            <fieldset>
                <legend>Обновить информацию</legend>
                <div class="form-floating mb-3">
                    <input type="text" name="name" value="${event.name}"
                           required class="form-control" id="formName" placeholder="название мероприятия">
                    <label for="formName">Название</label>
                </div>
                <div class="mb-3">
                    <select name="sportsComplex" required class="form-select">
                        <#list complexes?if_exists as complex >
                            <#if complex.id = event.sportsComplex.id>
                                <option value="${complex.id}" selected>${complex.name}</option>
                            <#else>
                                <option value="${complex.id}" selected>${complex.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <div class="form-floating mb-3">
                    <input type="datetime-local" name="startDateAndTime" value="${event.startDateAndTime}"
                           required class="form-control" id="formDate" placeholder="введите дату и время начала">
                    <label for="formDate">Дата и время начала</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="numberOfHours" value="${event.numberOfHours}"
                           required class="form-control" id="formNumberOfHours" placeholder="кол-во часов">
                    <label for="formNumberOfHours">Длительность (кол-во часов)</label>
                </div>
                <div class="mb-3">
                    <select name="trainer" required class="form-select">
                        <#list trainers?if_exists as trainer >
                            <#if trainer.id = event.trainer.id>
                                <option value="${trainer.id}" selected>${trainer.name}</option>
                            <#else>
                                <option value="${trainer.id}" selected>${trainer.name}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="price" value="${event.price}"
                           required class="form-control" id="formPrice" placeholder="стоимость участия">
                    <label for="formPrice">Стоимость участия</label>
                </div>
                <div>
                    <button style="width: 100%" type="submit" class="btn btn-primary">Обновить</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
    </div>
</@t.template>

