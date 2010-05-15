package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class ServerProxy extends IdentifiableRestletProxy {
	private String name;
	private String protocol;
	private int port;

	public void init(IConfigurationElement configElement) throws CoreException {
		super.init(configElement);
		name = configElement.getAttribute("name");
		protocol = configElement.getAttribute("protocol");
		port = Integer.valueOf(configElement.getAttribute("port"));
	}

	@Override
	public Restlet getRestlet() {
		return new Server(Protocol.valueOf(protocol), port);
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
