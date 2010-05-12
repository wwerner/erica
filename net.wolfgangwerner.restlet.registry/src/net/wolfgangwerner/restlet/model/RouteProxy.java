package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.IConfigurationElement;

public class RouteProxy extends RestletProxy {
	private String urlTemplate;
	private String targetId;

	public RouteProxy(IConfigurationElement configElement) {
		super(configElement);
		urlTemplate = configElement.getAttribute("urlTemplate");
		targetId = configElement.getAttribute("targetId");
	}

	public String getUrlTemplate() {
		return urlTemplate;
	}

	public String getTargetId() {
		return targetId;
	}

}
