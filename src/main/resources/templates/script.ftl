<#import "macros/common.ftl" as c>
<#import "macros/navbar.ftl" as n>

<@c.page>

    <@n.navbar_active "script"/>

    <form method="post">
        <div class="row mt-3">
            <div class="alert alert-warning mt-3" role="alert">
                <h4 class="alert-heading"> SQL script execution!</h4>
                <p>By clicking on the button below [Start Script], you will start cleaning and creating a new database which will contain the reference elements of computer components, a total of 40 pieces.</p>
                <hr>
                <p class="mb-0">Warning!!! If you click on the button below [Start Script] all data that was in the database will be destroyed!</p>
            </div>
        </div>

        <div class="row mt-3">
                <button type="submit" class="btn btn-primary mb-2 mt-3">Start Script</button>
        </div>
    </form>

</@c.page>