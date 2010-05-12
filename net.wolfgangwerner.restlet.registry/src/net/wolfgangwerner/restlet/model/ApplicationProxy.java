package net.wolfgangwerner.restlet.model;

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Application;
import org.restlet.Restlet;

public class ApplicationProxy extends IdentifyableRestletProxy {
	private String name;
	private Application application;
	private String outboundRootReference;

	public ApplicationProxy(IConfigurationElement configElement)
			throws CoreException {
		super(configElement);
		name = configElement.getAttribute("name");
		if (configElement.getAttribute("class") != null) {
			application = (Application) configElement
					.createExecutableExtension("class");
		}
		outboundRootReference = configElement
				.getAttribute("outboundRootReference");

	}

	@Override
	public Restlet getRestlet() {
		if (application != null)
			application = new Application();

		application.setOutboundRoot(RestletRegistry.getInstance().getRestlet(
				outboundRootReference));
		
		return application;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOutboundRootReference() {
		return outboundRootReference;
	}

}
