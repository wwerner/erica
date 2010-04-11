package net.wolfgangwerner.tutorial.restlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class Activator implements BundleActivator {
	private Component component;

	public void start(BundleContext context) throws Exception {
		component = new Component();
		component.getServers().add(Protocol.HTTP, 8182);

		Application mgmtApp = new Application();

		Router router = new Router();
		router.attach("/list", GreetingListResource.class);
		router.attach("/list/{mnemonic}", GreetingListResource.class);

		mgmtApp.setInboundRoot(router);

		component.getDefaultHost().attach("/tutorial2/manage", mgmtApp);
		component.getDefaultHost().attach("/tutorial2/greet",
				GreetingResource.class);

		component.start();
	}

	public void stop(BundleContext context) throws Exception {
		component.stop();
	}
}
