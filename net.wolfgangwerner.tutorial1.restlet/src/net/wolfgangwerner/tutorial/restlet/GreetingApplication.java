package net.wolfgangwerner.tutorial.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class GreetingApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/greet",GreetingResource.class);
		return router;
	}
}
