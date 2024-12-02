<#import "blocks/main-template.ftlh" as t>
<@t.template true true>
    <div class="col-md-6 mx-auto">
        <form action="/admin/sports-complex/update/${complex.id}" method="post">
            <fieldset>
                <legend>Обновить информацию</legend>
                <div class="form-floating mb-3">
                    <input type="text" name="name" value="${complex.name}"
                           required class="form-control" id="formName" placeholder="название спорткомплекса">
                    <label for="formName">Название</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" name="address" value="${complex.address}"
                           required class="form-control" id="formAddress" placeholder="Адрес спорткомплекса">
                    <label for="formAddress">Адрес</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="number" name="countSeats" value="${complex.countSeats}"
                           required class="form-control" id="formSeats" placeholder="Вместимость (кол-во мест)">
                    <label for="formSeats">Вместимость (кол-во мест)</label>
                </div>
                <div>
                    <button style="width: 100%" type="submit" class="btn btn-primary">Обновить</button>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
    </div>
</@t.template>




