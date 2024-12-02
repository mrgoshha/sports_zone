<#import "blocks/main-template.ftlh" as t>
<@t.template>
    <div class="col-md-6 mx-auto">
        <form action="/registration" method="post" class="mb-3">
            <fieldset>
                <legend>Регистрация</legend>
                <div class="form-floating mb-3">
                    <input type="text" name="name" required class="form-control" id="formName" placeholder="Имя">
                    <label for="formName">Имя</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" name="email" class="form-control" id="floatingInput"
                           placeholder="name@example.com">
                    <label for="floatingInput">Адрес электронной почты</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" name="password" class="form-control" id="floatingPassword"
                           placeholder="Пароль">
                    <label for="floatingPassword">Пароль</label>
                </div>
                <button style="width: 100%" type="submit" class="btn btn-primary">Зарегистрироваться</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </fieldset>
        </form>
        <#if errorMessage??>
            <div class="alert alert-danger text-center" role="alert">
                <h5 class="alert-heading">${errorMessage}</h5>
            </div>
        </#if>
    </div>
</@t.template>

