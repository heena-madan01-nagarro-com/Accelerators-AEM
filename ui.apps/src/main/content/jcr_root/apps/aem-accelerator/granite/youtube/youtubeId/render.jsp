<%--
  ADOBE CONFIDENTIAL
  ___________________

  Copyright 2013 Adobe
  All Rights Reserved.

  NOTICE: All information contained herein is, and remains
  the property of Adobe and its suppliers, if any. The intellectual
  and technical concepts contained herein are proprietary to Adobe
  and its suppliers and are protected by all applicable intellectual
  property laws, including trade secret and copyright laws.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe.
--%><%
%><%@ include file="/libs/granite/ui/global.jsp" %><%
%><%@ page session="false"
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

    // Start of attrs compatibility; please use cmp.populateCommonAttrs(attrs).
    attrs.add("id", cfg.get("id", String.class));
    attrs.addClass(cfg.get("class", String.class));
    attrs.addRel(cfg.get("rel", String.class));
    attrs.add("title", i18n.getVar(cfg.get("title", String.class)));
    attrs.addOthers(cfg.getProperties(), "id", "class", "rel", "title", "type", "name", "value", "emptyText", "disabled", "required", "validation", "maxlength", "fieldLabel", "fieldDescription", "renderReadOnly", "ignoreData");
    // End of attrs compatibility.

    attrs.add("type", "hidden");
    attrs.add("name", cfg.get("name", String.class));
    attrs.add("placeholder", i18n.getVar(cfg.get("emptyText", String.class)));
    attrs.addDisabled(cfg.get("disabled", false));
    attrs.add("autocomplete", cfg.get("autocomplete", String.class));
    attrs.addBoolean("autofocus", cfg.get("autofocus", false));

    if (isMixed) {
        attrs.addClass("foundation-field-mixed");
        attrs.add("placeholder", i18n.get("<Mixed Entries>"));
    } else {
        attrs.add("value", vm.get("value", String.class));
    }

    attrs.add("maxlength", cfg.get("maxlength", Integer.class));

    String fieldLabel = cfg.get("fieldLabel", String.class);
    String fieldDesc = cfg.get("fieldDescription", String.class);
    String labelledBy = null;

    if (fieldLabel != null && fieldDesc != null) {
        labelledBy = vm.get("labelId", String.class) + " " + vm.get("descriptionId", String.class);
    } else if (fieldLabel != null) {
        labelledBy = vm.get("labelId", String.class);
    } else if (fieldDesc != null) {
        labelledBy = vm.get("descriptionId", String.class);
    }

    if (StringUtils.isNotBlank(labelledBy)) {
        attrs.add("aria-labelledby", labelledBy);
    }

    if (cfg.get("required", false)) {
        attrs.add("aria-required", true);
    }

    String validation = StringUtils.join(cfg.get("validation", new String[0]), " ");
    attrs.add("data-validation", validation);

    attrs.addClass("coral-Textfield Hello");

%><input id="youtubeid" <%= attrs.build() %>>