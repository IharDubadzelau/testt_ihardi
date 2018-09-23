<#macro navbar_active menu>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mt-3">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link<#if menu == "listing"> active</#if>" href="/">Listing</a>
                <a class="nav-item nav-link<#if menu == "edit"> active</#if>" href="/edit/">Add</a>
                <a class="nav-item nav-link<#if menu == "script"> active</#if>" href="/script/">Script</a>
                <a class="nav-item nav-link<#if menu == "about"> active</#if>" href="/about/">About</a>
            </div>
        </div>
    </nav>
</#macro>