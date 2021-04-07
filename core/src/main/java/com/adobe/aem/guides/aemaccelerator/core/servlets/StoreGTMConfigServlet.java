package com.adobe.aem.guides.aemaccelerator.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=GTM service Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/apps/save/gtm" })
public class StoreGTMConfigServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreGTMConfigServlet.class);

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO add this to site level configuration

		final String destinationPath = "/etc/nagarro";
		final String gtmNodeName = "gtmconfig";
		Session session = request.getResourceResolver().adaptTo(Session.class);

		Map<String, String> map = new HashMap<>();
		LOGGER.info("parameter value" + request.getParameter("containerId"));
		map.put("containerId", request.getParameter("gtmContainerId"));
		map.put("headerScript", request.getParameter("gtmHeaderScript"));
		map.put("bodyScript", request.getParameter("gtmBodyScript"));
		try {
			String completePath = destinationPath+"/"+gtmNodeName;
			if(session.nodeExists(completePath)) {
				session.removeItem(completePath);
				session.save();
			}
			createNode(destinationPath, gtmNodeName, "nt:unstructured", map, session);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		response.getWriter().print("configuration saved Successfully at "+ destinationPath+"/"+gtmNodeName);
	}



	public void createNode(final String parentNodePath, final String nodeName, final String nodeType,
						   final Map<String, String> properties, Session session) throws RepositoryException {
		LOGGER.debug("Creating node '{}' of type '{}' under '{}'", nodeName, nodeType, parentNodePath);
		session.refresh(true);
		final Node parentNode = session.getNode(parentNodePath);
		Node createdNode = parentNode.addNode(nodeName, nodeType);
		if (properties != null) {
			for (Map.Entry<String, String> mapEntry : properties.entrySet()) {
				String key = mapEntry.getKey();
				String value = mapEntry.getValue();
				createdNode.setProperty(key, value);
			}
		}

		session.save();
	}
}
