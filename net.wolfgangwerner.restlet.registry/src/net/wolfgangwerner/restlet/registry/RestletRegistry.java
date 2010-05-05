package net.wolfgangwerner.restlet.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

public class RestletRegistry {
	private List<Component> components = new ArrayList<Component>();
	private Map<String, Class<ServerResource>> resources = new HashMap<String, Class<ServerResource>>();

	public List<Component> getComponents() {
		return components;
	}

	public void readExtensionRegistry() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException {
		readResources();
		readComponents();
	}

	public void readResources() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID, "resources");

		Bundle contributingBundle;
		Class<ServerResource> resourceClass;
		for (IConfigurationElement contribution : contributions) {
			contributingBundle = Platform.getBundle(contribution
					.getContributor().getName());
			resourceClass = (Class<ServerResource>) contributingBundle
					.loadClass(contribution.getAttribute("class"));

			resources.put(contribution.getAttribute("id"), resourceClass);
		}

	}

	public void readComponents() {
		IConfigurationElement[] componentContributions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, "components");

		Component component;
		for (IConfigurationElement configElement : componentContributions) {
			component = new Component();
			addServers(component, configElement);
			addResources(component, configElement);
			components.add(component);
		}
	}

	private void addResources(Component currentComponent,
			IConfigurationElement configElement) {
		for (IConfigurationElement resourceRef : configElement
				.getChildren("resourceReference")) {
			currentComponent.getDefaultHost().attach(
					resourceRef.getAttribute("urlTemplate"),
					resources.get(resourceRef.getAttribute("resourceId")));
		}
	}


	private void addServers(Component currentComponent,
			IConfigurationElement configElement) {
		for (IConfigurationElement serverConfig : configElement
				.getChildren("server")) {
			Protocol protocol = Protocol.valueOf(serverConfig
					.getAttribute("protocol"));
			currentComponent.getServers().add(protocol,
					Integer.valueOf(serverConfig.getAttribute("port")));
		}
	}
}
