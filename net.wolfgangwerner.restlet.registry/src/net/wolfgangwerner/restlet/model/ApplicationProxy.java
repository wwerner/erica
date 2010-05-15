package net.wolfgangwerner.restlet.model;

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Application;
import org.restlet.Restlet;

public class ApplicationProxy extends IdentifiableRestletProxy {
	private String name;
	private Application application;
	private String inboundRootReference;

	public void init(IConfigurationElement configElement)
			throws CoreException {
		super.init(configElement);
		name = configElement.getAttribute("name");
		if (configElement.getAttribute("class") != null) {
			application = (Application) configElement
					.createExecutableExtension("class");
		}
		inboundRootReference = configElement
				.getAttribute("inboundRoot");

	}

	@Override
	public Restlet getRestlet() {
		if (application == null)
			application = new Application();

		application.setInboundRoot(RestletRegistry.getInstance().getRestlet(
				inboundRootReference));
		
		return application;
	}

	public String getName() {
		return name;
	}

	public String getInboundRootReference() {
		return inboundRootReference;
	}

}
