package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;

public abstract class IdentifyableRestletProxy {
	protected String id;

	public IdentifyableRestletProxy(IConfigurationElement configElement) {
		id = configElement.getAttribute("id");
	}

	public abstract Restlet getRestlet();
}
