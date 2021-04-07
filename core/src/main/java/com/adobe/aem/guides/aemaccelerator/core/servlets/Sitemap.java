package com.adobe.aem.guides.aemaccelerator.core.servlets;


import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.xml.stream.*;

import org.apache.commons.lang3.time.FastDateFormat;

import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.*;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import javax.xml.stream.*;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */



@Component(service=Servlet.class,
           property={
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.resourceTypes="+ "aem-accelerator/components/structure/page",
                   "sling.servlet.extensions=" + "xml",
                   "sling.servlet.selectors="+"sitemap"
           })
@ServiceDescription("Sitemap Generation Servlet")

public class Sitemap extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 2L;
    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final String SITEMAP_NAMESPACE = "http://www.sitemaps.org/schemas/sitemap/0.9";
    @Reference
    private Externalizer externalizer;
    

    @Override
    protected void doGet(SlingHttpServletRequest slingRequest, SlingHttpServletResponse slingResponse)
    		 throws ServletException, IOException {
    		 
    		 slingResponse.setContentType(slingRequest.getResponseContentType());
    		 ResourceResolver resourceResolver = slingRequest.getResourceResolver();
    		 PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
    		 final Page pageObj = pageManager.getContainingPage(slingRequest.getResource());
    		 
    		 XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
    		 try {
    		 XMLStreamWriter stream = outputFactory.createXMLStreamWriter(slingResponse.getWriter());
    		 
    		 stream.writeStartDocument("1.0");
    		 stream.writeStartElement("", "urlset", SITEMAP_NAMESPACE);
    		 stream.writeNamespace("", SITEMAP_NAMESPACE);
    		 
    		 // Current page
    		 writeXML(pageObj, stream, slingRequest);
    		 
    		 for (Iterator<Page> children = pageObj.listChildren(new PageFilter(), true); children.hasNext();) {
             
    		 
    		 Page childPage = (Page) children.next();
    		 // If condition added to make sure the pages hidden in search in page properties do not show up in sitemap
    		 if (null != childPage) {
    		/* if (!childPage.getProperties().containsKey("hideInSearch")
    		 || (childPage.getProperties().containsKey("hideInSearch")
    		 && childPage.getProperties().get("hideInSearch").equals("false"))
    		 || (childPage.getProperties().containsKey("hideInSearch")
    		 && childPage.getProperties().get("hideInSearch").equals("")))*/
    		 writeXML(childPage, stream, slingRequest);
    		 }
    		 }
    		 
    		 stream.writeEndElement();
    		 stream.writeEndDocument();
    		 
    		 } catch (XMLStreamException e) {
    		 throw new IOException(e);
    		 }
    		 }
    		 
    		 private void writeXML(Page pageObj, XMLStreamWriter xmlStream, SlingHttpServletRequest slingRequest)
    		 throws XMLStreamException {
    		 xmlStream.writeStartElement(SITEMAP_NAMESPACE, "url");
    		 
    		 String protocolPort = "http";
    		 if (slingRequest.isSecure())
    		 protocolPort = "https";
    		 
    		 String locPath = this.externalizer.absoluteLink(slingRequest, protocolPort,
    		 String.format("%s.html", pageObj.getPath()));
    		 
    		 writeXMLElement(xmlStream, "loc", locPath);
    		 
    		 //if (this.incLastModified) {
    		 Calendar calendarObj = pageObj.getLastModified();
    		 if (null != calendarObj) {
    		 writeXMLElement(xmlStream, "lastmod", DATE_FORMAT.format(calendarObj));
    		 }
    		 //}
    		 xmlStream.writeEndElement();
    		 }
    		 
    		 private void writeXMLElement(final XMLStreamWriter xmlStream, final String elementName, final String xmlText)
    		 throws XMLStreamException {
    		 xmlStream.writeStartElement(SITEMAP_NAMESPACE, elementName);
    		 xmlStream.writeCharacters(xmlText);
    		 xmlStream.writeEndElement();
    		 }
    		 
}


