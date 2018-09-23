<table class="table table-striped mb-5">
    <thead class="thead-light">
    <tr>
        <th scope="col-6">Name Parts</th>
        <th scope="col">Need for PC</th>
        <th scope="col">Count</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <#list page_parts.content as part>
    <tr>
        <td>${part.name}</td>
        <td><#if part.needs >yes<#else>no</#if></td>
        <td>${part.count}</td>
        <td>
            <a href="/edit?id=${part.id}<#if page_parts??><#if page_parts.getTotalPages() gt 1>&page=${page_parts.getNumber()}</#if></#if>">edit</a>
        </td>
        <td>
            <a href="/delete?id=${part.id}<#if page_parts??><#if page_parts.getTotalPages() gt 1>&page=${page_parts.getNumber()}</#if></#if>">delete</a>
        </td>
    </tr>
    </#list>
    </tbody>
</table>