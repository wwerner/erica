package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.IConfigurationElement;

public class FilterProxy extends RestletProxy {
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public String getTargetId() {
		return targetId;
	}

	private String name;
	private String className;
	private String targetId;

	public FilterProxy(IConfigurationElement configElement) {
		super(configElement);
		id = configElement.getAttribute("targetId");
		name = configElement.getAttribute("name");
		className = configElement.getAttribute("class");
		targetId = configElement.getAttribute("next");
	}



}
