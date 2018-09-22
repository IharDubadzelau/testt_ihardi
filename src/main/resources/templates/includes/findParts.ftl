<form class="form-inline mt-3">
    <div class="form-group mb-2">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio_box" id="inlineRadio1" value="all" <#if radio_box=="all">checked</#if>>
            <label class="form-check-label" for="inlineRadio1">ALL</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio_box" id="inlineRadio2" value="yes" <#if radio_box=="yes">checked</#if>>
            <label class="form-check-label" for="inlineRadio2">yes</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio_box" id="inlineRadio3" value="no" <#if radio_box=="no">checked</#if>>
            <label class="form-check-label" for="inlineRadio3">no</label>
        </div>
    </div>
    <div class="form-group mx-sm-3 mb-2">
        <input type="text" name="name_filter" class="form-control" value="${name_filter?ifExists}"/>
    </div>
    <button type="submit" class="btn btn-primary mb-2">Find</button>
</form>