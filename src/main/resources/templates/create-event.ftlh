<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="col-md-6 mx-auto">
        <form action="/admin/sports-event/create" method="post">
            <fieldset>
                <legend>Новое мероприятие</legend>
                <div class="form-floating mb-3">
                    <input type="text" name="name" required class="form-control" id="formName" placeholder="название мероприятия">
                    <label for="formName">Название</label>
                </div>
                <div class="mb-3">
                    <select name="sportsComplex" required class="form-select">
                        <option value="">Выберите спорткомплекс</option>
                        <#list complexes as complex>
                            <option value="${complex.id}">${complex.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-floating mb-3">
                    <input type="datetime-local" name="startDateAndTime" required class="form-control" id="formDate"
                           placeholder="введите дату и время начала">
                    <label for="formDate">Дата и время начала</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="numberOfHours" required class="form-control" id="formNumberOfHours" placeholder="кол-во часов">
                    <label for="formNumberOfHours">Длительность (кол-во часов)</label>
                </div>
                <div class="mb-3">
                    <select name="trainer" required class="form-select">
                        <option value="" selected disabled>Выберите тренера</option>
                        <#list trainers as trainer>
                            <option value="${trainer.id}">${trainer.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="price" required class="form-control" id="formPrice" placeholder="стоимость участия">
                    <label for="formPrice">Стоимость участия</label>
                </div>
                <div>
                    <button style="width: 100%" type="submit" class="btn btn-primary">Добавить</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
    </div>
</@t.template>