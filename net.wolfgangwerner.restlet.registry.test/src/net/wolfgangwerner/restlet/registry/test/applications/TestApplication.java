package net.wolfgangwerner.restlet.registry.test.applications;

import net.wolfgangwerner.restlet.registry.test.resources.Resource1;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class TestApplication extends Application {
	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/1",Resource1.class);
		return router;
	}
}
