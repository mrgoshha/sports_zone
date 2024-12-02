<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="col-md-6 mx-auto">
        <form action="/admin/sports-complex/create" method="post">
            <fieldset>
                <legend>Новый спорткомплекс</legend>
                <div class="form-floating mb-3">
                    <input type="text" name="name" required class="form-control" id="formName" placeholder="название спорткомплекса">
                    <label for="formName">Название спорткомплекса</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="address" required class="form-control" id="formAddress" placeholder="Адрес">
                    <label for="formAddress">Адрес</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="countSeats" required class="form-control" id="formSeats" placeholder="количество мест">
                    <label for="formSeats">Количество мест</label>
                </div>
                <div>
                    <button style="width: 100%" type="submit" class="btn btn-primary">Добавить</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
    </div>
</@t.template>
