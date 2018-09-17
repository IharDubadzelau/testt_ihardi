<#import "macros/common.ftl" as c>
<@c.page>
<div>
    <form method="post">
        <input type="text" name="name" placeholder="Наименование" />
        <input type="checkbox" name="needs" placeholder="Необходимо для PC" />
        <input type="number" name="count" min="1" placeholder="Кол-во" />
        <button type="submit">Добавить</button>
    </form>
</div>
<div>------- Фильтр -------</div>
<div>
    <form method="get">
        <input type="text" name="name_filter" placeholder="Наименование" value="${name_filter}"/>
        <!--<input type="checkbox" name="needs_filter" placeholder="Необходимо для PC" />-->
        <button type="submit">Найти</button>
    </form>
</div>
<div>------- Список -------</div>
<#list parts as part>
<div>
    <b>${part.id}</b>
    <span>${part.name}</span>
    <span><#if part.needs >Да<#else>Нет</#if></span>
    <i>${part.count}</i>
</div>
<#else>
<span><#if is_filter >По фильтру: ${name_filter} - запчастей нет<#else>В базе данных нет запчастей.</#if></span>
</#list>

</@c.page>