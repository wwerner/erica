package net.wolfgangwerner.restlet.model;

import java.util.ArrayList;
import java.util.List;

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RouterProxy extends IdentifiableRestletProxy {
	private List<RouteProxy> routes = new ArrayList<RouteProxy>();

	public void init (IConfigurationElement configElement) throws CoreException {
		super.init(configElement);

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

	public List<RouteProxy> getRoutes() {
		return routes;
	}
}
