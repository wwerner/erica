package net.wolfgangwerner.restlet.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.wolfgangwerner.restlet.model.ApplicationProxy;
import net.wolfgangwerner.restlet.model.ComponentProxy;
import net.wolfgangwerner.restlet.model.IdentifyableRestletProxy;
import net.wolfgangwerner.restlet.model.RouterProxy;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;

public class RestletRegistry {

	static RestletRegistry instance = new RestletRegistry();

	private Map<String, ComponentProxy> components = new HashMap<String, ComponentProxy>();
	private Map<String, Class<ServerResource>> resources = new HashMap<String, Class<ServerResource>>();
	private Map<String, ApplicationProxy> applications = new HashMap<String, ApplicationProxy>();
	private Map<String, RouterProxy> routers = new HashMap<String, RouterProxy>();
	private Map<String, IdentifyableRestletProxy> allRestlets = new HashMap<String, IdentifyableRestletProxy>();

	private RestletRegistry() {
		super();
	}

	public static RestletRegistry getInstance() {
		return instance;
	}

	public void readExtensionRegistry() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException {
		// TODO Build a generic registry. This is grossly redundant.

		readResources();
		readComponents();
		readApplications();
		readRouters();

		allRestlets.putAll(components);
		allRestlets.putAll(applications);
		allRestlets.putAll(routers);
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

	@SuppressWarnings("unchecked")
	private void readResources() throws InvalidRegistryObjectException,
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

	private void readComponents() {
		IConfigurationElement[] componentContributions = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						Activator.PLUGIN_ID, "components");

		for (IConfigurationElement configElement : componentContributions) {
			ComponentProxy proxy = new ComponentProxy(configElement);
			components.put(proxy.getId(), proxy);
		}
	}

	public boolean isRestletId(String restletId) {
		return allRestlets.containsKey(restletId);
	}

	public Restlet getRestlet(String restletId) {
		return allRestlets.get(restletId).getRestlet();
	}

	public Class<ServerResource> getResource(String resourceId) {
		return resources.get(resourceId);
	}

	public List<Component> getComponents() {
		List<Component> result = new ArrayList<Component>();
		for (ComponentProxy proxy : components.values()) {
			result.add((Component) proxy.getRestlet());
		}
		return result;
	}
}
