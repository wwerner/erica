package net.wolfgangwerner.restlet.model;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class RouterProxy extends RestletProxy {
	private String name;
	private List<RouteProxy> routes;

	public RouterProxy(IConfigurationElement configElement) {
		super(configElement);
		id = configElement.getAttribute("id");
		name = configElement.getAttribute("name");

		for (IConfigurationElement routeConfig : configElement
				.getChildren("route")) {
			routes.add(new RouteProxy(routeConfig));
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<RouteProxy> getRoutes() {
		return routes;
	}
}
