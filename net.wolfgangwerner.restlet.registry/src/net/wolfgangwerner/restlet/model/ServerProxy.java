package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.IConfigurationElement;

public class ServerProxy extends RestletProxy {
	private String name;
	private String protocol;
	private int port;

	public ServerProxy(IConfigurationElement configElement) {
		super(configElement);
		name = configElement.getAttribute("name");
		protocol = configElement.getAttribute("protocol");
		port = Integer.valueOf(configElement.getAttribute("port"));
	}

	public String getName() {
		return name;
	}

	public String getProtocol() {
		return protocol;
	}

	public int getPort() {
		return port;
	}
}
