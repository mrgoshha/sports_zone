<#import "blocks/main-template.ftlh" as t>
<@t.template>
    <div class="col-md-6 mx-auto">
        <form action="/new-password" method="post" class="mb-3">
            <fieldset>
                <legend>Восстановление пароля</legend>
                <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="floatingPassword"
                           placeholder="Пароль">
                    <label for="floatingPassword">Пароль</label>
                </div>
                <button style="width: 100%" type="submit" class="btn btn-primary">Обновить пароль</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
    </div>
</@t.template>


