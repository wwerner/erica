package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;

public abstract class IdentifiableRestletProxy {
	protected String id;
	
	public void init(IConfigurationElement configElement) throws CoreException{
		id = configElement.getAttribute("id");
	}
	
	public String getId() {
		return id;
	}

	public abstract Restlet getRestlet();
}
