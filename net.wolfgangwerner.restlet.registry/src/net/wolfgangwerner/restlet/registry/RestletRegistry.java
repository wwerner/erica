package net.wolfgangwerner.restlet.registry;

import java.util.HashMap;
import java.util.Map;

import net.wolfgangwerner.restlet.model.ApplicationProxy;
import net.wolfgangwerner.restlet.model.ComponentProxy;
import net.wolfgangwerner.restlet.model.FilterProxy;
import net.wolfgangwerner.restlet.model.RestletProxy;
import net.wolfgangwerner.restlet.model.RouterProxy;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.restlet.resource.ServerResource;

public class RestletRegistry {
	private Map<String, ComponentProxy> components = new HashMap<String, ComponentProxy>();
	private Map<String, Class<ServerResource>> resources = new HashMap<String, Class<ServerResource>>();
	private Map<String, ApplicationProxy> applications = new HashMap<String, ApplicationProxy>();
	private Map<String, RouterProxy> routers = new HashMap<String, RouterProxy>();
	private Map<String, FilterProxy> filters = new HashMap<String, FilterProxy>();
	private Map<String, RestletProxy> allRestlets = new HashMap<String, RestletProxy>();

	public void readExtensionRegistry() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException {
		readResources();
		readComponents();
		readApplications();
		readRouters();
		readFilters();

		allRestlets.putAll(components);
		allRestlets.putAll(applications);
		allRestlets.putAll(routers);
		allRestlets.putAll(filters);

	}

	private void readApplications() throws CoreException {
		IConfigurationElement[] applicationContributions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, "applications");

		for (IConfigurationElement configElement : applicationContributions) {
			ApplicationProxy proxy = new ApplicationProxy(configElement);
			applications.put(proxy.getId(), proxy);
		}
	}

	private void readRouters() throws CoreException {
		IConfigurationElement[] applicationContributions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, "routers");

		for (IConfigurationElement configElement : applicationContributions) {
			RouterProxy proxy = new RouterProxy(configElement);
			routers.put(proxy.getId(), proxy);
		}
	}

	private void readFilters() throws CoreException {
		IConfigurationElement[] applicationContributions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, "filters");

		for (IConfigurationElement configElement : applicationContributions) {
			FilterProxy proxy = new FilterProxy(configElement);
			filters.put(proxy.getId(), proxy);
		}
	}

	@SuppressWarnings("unchecked")
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

		for (IConfigurationElement configElement : componentContributions) {
			ComponentProxy proxy = new ComponentProxy(configElement);
			components.put(proxy.getId(), proxy);
		}
	}
}
