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
          import="com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Tag" %><%

    // @deprecated

    Config cfg = cmp.getConfig();

	Tag tag = cmp.consumeTag();
    AttrBuilder attrs = tag.getAttrs();

    String fieldLabel = cfg.get("fieldLabel", String.class);
    String value = cmp.getValue().val(cmp.getExpressionHelper().getString(cfg.get("value", "")));

    if (cmp.getOptions().rootField()) {
        attrs.addClass("coral-Form-fieldwrapper");

        %><span <%= attrs.build() %>><%
	        if (fieldLabel != null) {
	            %><label class="coral-Form-fieldlabel"><%= outVar(xssAPI, i18n, fieldLabel) %></label><%
	        }
	        %><span class="coral-Form-field foundation-layout-util-breakword"><%= xssAPI.encodeForHTML(value) %></span
        ></span><%
    } else {
        %><span <%= attrs.build() %>><%= xssAPI.encodeForHTML(value) %></span><%
    }
%>