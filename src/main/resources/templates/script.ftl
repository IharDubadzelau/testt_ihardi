<#import "macros/common.ftl" as c>
<#import "macros/navbar.ftl" as n>

<@c.page>

    <@n.navbar_active "script"/>

    <form method="post">
        <div class="alert alert-warning mt-3 " role="alert">
            <h4 class="alert-heading text-sm-center"> SQL script execution!</h4>
            <p class="text-sm-center">By clicking on the button below [Start Script], you will start cleaning and creating a new database which will contain the reference elements of computer components, a total of 41 pieces.</p>
            <hr>
            <p class="text-sm-center mb-0">Warning!!! If you click on the button below [Start Script] all data that was in the database will be destroyed!</p>
        </div>

        <div class="col-sm text-sm-center">
            <button type="submit" class="btn btn-primary text-sm-center mb-2 mt-3">Start Script</button>
        </div>
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