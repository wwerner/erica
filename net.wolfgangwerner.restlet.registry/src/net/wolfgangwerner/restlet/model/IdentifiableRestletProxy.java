package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;

public abstract class IdentifiableRestletProxy {
	protected String id;
	protected String name;
	
	public void init(IConfigurationElement configElement) throws CoreException{
		id = configElement.getAttribute("id");
		name = configElement.getAttribute("name");
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public abstract Restlet getRestlet();
}
