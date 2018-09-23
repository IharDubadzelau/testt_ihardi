<#import "macros/common.ftl" as c>
<#import "macros/pageParts.ftl" as p>
<#import "macros/navbar.ftl" as n>

<@c.page>
    <@n.navbar_active "listing"/>
    <#include "includes/findParts.ftl" />
    <#include "includes/line.ftl" />
    <#include "includes/sortParts.ftl" />
    <#include "includes/line.ftl" />
    <@p.page_parts url page_parts/>
    <#include "includes/tableParts.ftl" />
    <@p.page_parts url page_parts/>
    <#include "includes/line.ftl" />
</@c.page>