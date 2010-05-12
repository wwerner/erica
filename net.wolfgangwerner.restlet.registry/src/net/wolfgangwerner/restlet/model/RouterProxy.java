package net.wolfgangwerner.restlet.model;

import java.util.ArrayList;
import java.util.List;

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RouterProxy extends IdentifyableRestletProxy {
	private String name;
	private List<RouteProxy> routes = new ArrayList<RouteProxy>();

	public RouterProxy(IConfigurationElement configElement) {
		super(configElement);
		id = configElement.getAttribute("id");
		name = configElement.getAttribute("name");

		for (IConfigurationElement routeConfig : configElement
				.getChildren("route")) {
			routes.add(new RouteProxy(routeConfig));
		}
	}
	
	@Override
	public Restlet getRestlet() {
		Router router = new Router();

		for (RouteProxy route : routes) {
			if (RestletRegistry.getInstance().isRestletId(route.getTargetId()))
				router.attach(route.getUrlTemplate(), RestletRegistry
						.getInstance().getRestlet(route.getTargetId()));
			else
				router.attach(route.getUrlTemplate(), RestletRegistry
						.getInstance().getResource(route.getTargetId()));
		}
		
		return router;
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
