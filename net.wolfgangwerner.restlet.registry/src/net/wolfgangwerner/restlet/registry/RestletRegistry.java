package net.wolfgangwerner.restlet.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.wolfgangwerner.restlet.model.ApplicationProxy;
import net.wolfgangwerner.restlet.model.ComponentProxy;
import net.wolfgangwerner.restlet.model.IdentifiableRestletProxy;
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

	private Map<String, ComponentProxy> components;
	private Map<String, ApplicationProxy> applications;
	private Map<String, RouterProxy> routers;

	private Map<String, Class<ServerResource>> resources = new HashMap<String, Class<ServerResource>>();
	private Map<String, IdentifiableRestletProxy> allRestlets = new HashMap<String, IdentifiableRestletProxy>();

	private RestletRegistry() {
		super();
	}

	public static RestletRegistry getInstance() {
		return instance;
	}

	public void readExtensionRegistry() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException, InstantiationException, IllegalAccessException {
		readResources();
		components = read("components",ComponentProxy.class);
		applications= read("applications",ApplicationProxy.class);
		routers= read("routers",RouterProxy.class);

		allRestlets.putAll(components);
		allRestlets.putAll(applications);
		allRestlets.putAll(routers);
	}

	private <T extends IdentifiableRestletProxy> Map<String, T> read(String extensionPointId, Class<T> proxyClass)
			throws CoreException, InstantiationException, IllegalAccessException {
		Map<String, T> result = new HashMap<String, T>();
		IConfigurationElement[] contributions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID,
						extensionPointId);

		for (IConfigurationElement configElement : contributions) {
			T proxy =  proxyClass.newInstance();
			proxy.init(configElement);
			result.put(proxy.getId(), proxy);
		}
		
		return result;
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

	public boolean isRestletId(String restletId) {
		return allRestlets.containsKey(restletId);
	}

	public Restlet getRestlet(String restletId) {
		assert(restletId != null);
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
