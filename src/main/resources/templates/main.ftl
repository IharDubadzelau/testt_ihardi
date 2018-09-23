<#import "macros/common.ftl" as c>
<#import "macros/pageParts.ftl" as p>
<#import "macros/navbar.ftl" as n>

<@c.page>
    <@n.navbar_active "listing"/>

    <#if error??>
        <#include "includes/line.ftl" />
        <div class="alert alert-danger text-sm-center" role="alert">
            ${error}
        </div>
    </#if>

    <#include "includes/findParts.ftl" />
    <#include "includes/line.ftl" />
    <@p.page_parts url page_parts/>
    <#include "includes/tableParts.ftl" />
    <@p.page_parts url page_parts/>
    <#include "includes/line.ftl" />
    <#include "includes/tableResult.ftl" />
</@c.page>