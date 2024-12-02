<#import "blocks/main-template.ftlh" as t>
<@t.template>
    <div class="col-md-6 mx-auto">
        <form action="/login-reset" method="post" class="mb-3">
            <fieldset>
                <legend>Восстановление пароля</legend>
                <div class="form-floating mb-3">
                    <input type="email" name="email" class="form-control" id="floatingInput"
                           placeholder="name@example.com">
                    <label for="floatingInput">Адрес электронной почты</label>
                </div>
                <button style="width: 100%" type="submit" class="btn btn-primary">Получить временный пароль</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>

        <#if resetMessage??>
            <div class="alert alert-success text-center" role="alert">
                <h5 class="alert-heading">${resetMessage}</h5>
            </div>
            <form action="/recover-password" method="post" class="mb-3">
                <fieldset>
                    <legend>Введите временный пароль</legend>
                    <#if errorMessage??>
                        <div class="form-floating mb-3">
                            <input type="password" name="password" class="form-control is-invalid"
                                   id="floatingInputInvalid" placeholder="Попробуйте ещё раз">
                            <label for="floatingInputInvalid">Попробуйте ещё раз</label>
                        </div>
                    <#else>
                        <div class="form-floating mb-3">
                            <input type="password" name="password" class="form-control" id="floatingPassword"
                                   placeholder="Пароль">
                            <label for="floatingPassword">Пароль</label>
                        </div>
                    </#if>
                    <button style="width: 100%" type="submit" class="btn btn-primary">Восстановить пароль</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </fieldset>
            </form>
        <#elseif errorMessage??>
            <div class="alert alert-danger text-center" role="alert">
                <h5 class="alert-heading">${errorMessage}</h5>
            </div>
        </#if>
    </div>
</@t.template>


