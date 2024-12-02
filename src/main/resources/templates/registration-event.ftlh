<#import "blocks/main-template.ftlh" as t>
<@t.template true admin>
    <div class="card mb-2">
        <#if !errorMessage??>
            <h4 class="card-header text-center">Вы успешно подали заявку на регистрацию на мероприятие ${event.name}! </h4>
        <#else>
            <h4 class="card-header text-center">Регистрация на мероприятие ${event.name} </h4>
        </#if>
        <div class="card-body">
            <h6 class="card-header text-muted mb-2">Подробная информация</h6>
            <p class="card-text">Спорткомплекс: ${event.sportsComplex.name}</p>
            <p class="card-text">Дата и время начала: ${event.startDateAndTime}</p>
            <p class="card-text">Длительность(в часах): ${event.numberOfHours}</p>
            <p class="card-text">Тренер: ${event.trainer.name}</p>
            <p class="card-text">Стоимость участия: ${event.price}</p>
            <p class="card-text">Кол-во свободных мест: ${event.sportsComplex.countSeats - event.countSeats}
        </div>
    </div>
    <#if errorMessage??>
        <div class="alert alert-danger text-center" role="alert">
            <h5 class="alert-heading">${errorMessage}</h5>
        </div>
    </#if>
</@t.template>

