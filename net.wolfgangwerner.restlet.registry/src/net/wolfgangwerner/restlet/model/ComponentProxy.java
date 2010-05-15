package net.wolfgangwerner.restlet.model;

import java.util.ArrayList;
import java.util.List;

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;

public class ComponentProxy extends IdentifiableRestletProxy {
	private String name;
	private List<ServerProxy> servers = new ArrayList<ServerProxy>();
	private List<RouteProxy> routes = new ArrayList<RouteProxy>();

	public void init(IConfigurationElement configElement) throws CoreException {
		super.init(configElement);
		name = configElement.getAttribute("name");

		for (IConfigurationElement serverConfig : configElement
				.getChildren("server")) {
			ServerProxy serverProxy = new ServerProxy();
			serverProxy.init(serverConfig);
			servers.add(serverProxy);
		}

		for (IConfigurationElement routeConfig : configElement
				.getChildren("route")) {
			routes.add(new RouteProxy(routeConfig));
		}
	}

	@Override
	public Restlet getRestlet() {
		Component component = new Component();

		for (ServerProxy serverProxy : servers) {
			component.getServers().add((Server) serverProxy.getRestlet());
		}

		for (RouteProxy route : routes) {
			if (RestletRegistry.getInstance().isRestletId(route.getTargetId()))
				component.getDefaultHost().attach(
						route.getUrlTemplate(),
						RestletRegistry.getInstance().getRestlet(
								route.getTargetId()));
			else
				component.getDefaultHost().attach(
						route.getUrlTemplate(),
						RestletRegistry.getInstance().getResource(
								route.getTargetId()));
		}

		return component;
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
