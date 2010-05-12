package net.wolfgangwerner.restlet.model;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

public class ComponentProxy extends RestletProxy {
	private String name;
	private List<ServerProxy> servers;
	private List<RouteProxy> routes;
	

	public ComponentProxy(IConfigurationElement configElement) {
		super(configElement);
		id = configElement.getAttribute("id");
		name = configElement.getAttribute("name");
		
		for (IConfigurationElement serverConfig : configElement
				.getChildren("server")) {
			servers.add(new ServerProxy(serverConfig));
		}
		
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
	
	public List<ServerProxy> getServers() {
		return servers;
	}

	public List<RouteProxy> getRoutes() {
		return routes;
	}
}
