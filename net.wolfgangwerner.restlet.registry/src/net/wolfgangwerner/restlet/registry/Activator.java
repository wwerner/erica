package net.wolfgangwerner.restlet.registry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.restlet.Component;

public class Activator implements BundleActivator {
	public static final String PLUGIN_ID = "net.wolfgangwerner.restlet.registry";

	private RestletRegistry registry;

	public void start(BundleContext context) throws Exception {
		registry = new RestletRegistry();
		registry.readExtensionRegistry();
		for (Component component : registry.getComponents()) {
			component.start();
		}
	}

	public void stop(BundleContext context) throws Exception {
		for (Component component : registry.getComponents()) {
			component.stop();
		}
	}

}
