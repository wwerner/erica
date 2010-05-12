package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.IConfigurationElement;

public class ApplicationProxy extends RestletProxy {
	private String name;
	private String className;
	private String outboundRootReference;

	public ApplicationProxy(IConfigurationElement configElement) {
		super(configElement);
		id = configElement.getAttribute("id");
		name = configElement.getAttribute("name");
		className = configElement.getAttribute("class");
		outboundRootReference = configElement
				.getAttribute("outboundRootReference");

	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getOutboundRootReference() {
		return outboundRootReference;
	}

}
