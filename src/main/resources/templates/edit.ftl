<#import "macros/common.ftl" as c>
<#import "macros/navbar.ftl" as n>

<@c.page>

    <@n.navbar_active "edit"/>

    <form method="post">
        <div class="row mt-3">
            <div class="col-1">
                <input type="text" class="form-control" name="id" placeholder="id" value="<#if part??>${part.id}<#else >new</#if>" disabled>
            </div>
            <div class="col-6">
                <input type="text" class="form-control"  name="name" value="<#if part??>${part.name}</#if>" placeholder="Name part">
            </div>
            <div class="col">
                <input type="number" class="form-control" name="count" min="0" value="<#if part??>${part.count}</#if>" placeholder="Count">
            </div>
            <div class="col mt-2">
                <input type="checkbox" class="needs"  name="needs" <#if part??><#if part.needs>checked="~"</#if></#if>>
                <label for="formGroupExampleInput">Need for PC </label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary mb-2 mt-3"><#if part??>Modify<#else >Add</#if></button>
    </form>

    <#if message??>
      <div class="alert alert-success text-sm-center" role="alert">
          ${message}
      </div>
    </#if>

    <#if error??>
      <div class="alert alert-danger text-sm-center" role="alert">
          ${error}
      </div>
    </#if>

</@c.page>