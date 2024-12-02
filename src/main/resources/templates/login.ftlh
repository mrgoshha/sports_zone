<#import "blocks/main-template.ftlh" as t>
<@t.template>
    <div class="col-md-6 mx-auto">
        <form action="/login" method="post" class="mb-3">
            <fieldset>
                <legend>Авторизация</legend>
                <div class="form-floating mb-3">
                    <input type="email" name="username" class="form-control" id="floatingInput"
                           placeholder="name@example.com">
                    <label for="floatingInput">Адрес электронной почты</label>
                </div>
                <#if errorMessage??>
                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control is-invalid"
                               id="floatingInputInvalid" placeholder="Попробуйте ещё раз">
                        <label for="floatingInputInvalid">Попробуйте ещё раз</label>
                    </div>
                    <div class="mb-3">
                        <a role="button" style="width: 100%" href="/login-reset" class="btn btn-outline-success">Восстановление пароля</a>
                    </div>
                <#else>
                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="floatingPassword"
                               placeholder="Пароль">
                        <label for="floatingPassword">Пароль</label>
                    </div>
                </#if>
                <button style="width: 100%" type="submit" class="btn btn-primary">Войти</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
        <h3 class="text-center">Нет аккаунта SPORTSZone? <a href="/registration">Зарегистрируйся!</a></h3>
    </div>
</@t.template>




