<link rel="stylesheet" type="text/css" href="/apps/aem-accelerator/granite/youtube/youtubeSearch/css/index.css">
<%@ include file="/libs/granite/ui/global.jsp" %>
<%@ page session="false"
          import="org.apache.commons.lang3.StringUtils,
                  com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Field,
                  com.adobe.granite.ui.components.Tag" %>

<%

    Config cfg = cmp.getConfig();
    ValueMap vm = (ValueMap) request.getAttribute(Field.class.getName());
    Field field = new Field(cfg);

	boolean isMixed = field.isMixed(cmp.getValue());
	Tag tag = cmp.consumeTag();
    AttrBuilder attrs = tag.getAttrs();
    cmp.populateCommonAttrs(attrs);

	attrs.add("type", "text");
    attrs.add("name", cfg.get("name", String.class));
    attrs.add("placeholder", i18n.getVar(cfg.get("emptyText", String.class)));
    attrs.addDisabled(cfg.get("disabled", false));
    attrs.add("autocomplete", cfg.get("autocomplete", String.class));
    attrs.addBoolean("autofocus", cfg.get("autofocus", false));
	attrs.add("id", "query");
	if (cfg.get("required", false)) {
        attrs.add("aria-required", true);
    }
    if (isMixed) {
        attrs.addClass("foundation-field-mixed");
        attrs.add("placeholder", i18n.get("<Mixed Entries>"));
    } else {
        attrs.add("value", vm.get("value", String.class));
    }
   
	attrs.addClass("coral-Textfield");
	attrs.addClass("form-control width100");
%>
<div class="searchDiv">
	<input <%= attrs.build() %> >
</div>
<%@ include file="/apps/aem-accelerator/granite/youtube/youtubeSearch/index.html" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/client.js?onload=googleApiClientReady"></script>
<script type="text/javascript" src="/apps/aem-accelerator/granite/youtube/main.js"></script>
