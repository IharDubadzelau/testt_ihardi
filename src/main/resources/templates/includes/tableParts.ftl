<table class="table table-striped mb-5">
    <thead class="thead-dark">
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
        <td><#if part.needs >yes<#else>not</#if></td>
        <td>${part.count}</td>
        <td>
            <a href="/edit?id=${part.id}">edit</a>
        </td>
        <td>
            <a href="/delete?id=${part.id}">delete</a>
        </td>
    </tr>
    </#list>
    </tbody>
</table>