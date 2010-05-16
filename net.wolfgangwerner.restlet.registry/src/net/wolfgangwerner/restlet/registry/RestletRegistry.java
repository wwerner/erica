package net.wolfgangwerner.restlet.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
	private static final Logger logger = Logger.getLogger(RestletRegistry.class
			.getName());

	static RestletRegistry instance = new RestletRegistry();

	private Map<String, ComponentProxy> components;

	private Map<String, Class<ServerResource>> resources = new HashMap<String, Class<ServerResource>>();
	private Map<String, IdentifiableRestletProxy> allRestlets = new HashMap<String, IdentifiableRestletProxy>();

	private RestletRegistry() {
		super();
	}

	public static RestletRegistry getInstance() {
		return instance;
	}

	public void readExtensionRegistry() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException, InstantiationException,
			IllegalAccessException {
		logger.info("Started reading extension information.");
		readResources();
		components = readRestlets("components", ComponentProxy.class);
		readRestlets("applications", ApplicationProxy.class);
		readRestlets("routers", RouterProxy.class);
	}

	private <T extends IdentifiableRestletProxy> Map<String, T> readRestlets(
			String extensionPointId, Class<T> proxyClass) throws CoreException,
			InstantiationException, IllegalAccessException {
		logger.info("Evaluating extentsion point " + Activator.PLUGIN_ID + "."
				+ extensionPointId);
		Map<String, T> result = new HashMap<String, T>();
		IConfigurationElement[] contributions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID,
						extensionPointId);

		for (IConfigurationElement configElement : contributions) {
			T proxy = proxyClass.newInstance();
			proxy.init(configElement);
			logger.info("Adding restlet " + proxy);
			result.put(proxy.getId(), proxy);
			allRestlets.put(proxy.getId(), proxy);
		}

		logger.info("Found " + result.size() + " contributions to "
				+ Activator.PLUGIN_ID + "." + extensionPointId);
		return result;
	}

	@SuppressWarnings("unchecked")
	private void readResources() throws InvalidRegistryObjectException,
			ClassNotFoundException, CoreException {
		logger.info("Evaluating extentsion point " + Activator.PLUGIN_ID + "."
				+ "resources");

		IConfigurationElement[] contributions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(Activator.PLUGIN_ID, "resources");

		Bundle contributingBundle;
		Class<ServerResource> resourceClass;
		String name;
		String id;
		String clazz;
		for (IConfigurationElement contribution : contributions) {
			id = contribution.getAttribute("id");
			name = contribution.getAttribute("name");
			clazz = contribution.getAttribute("class");

			contributingBundle = Platform.getBundle(contribution
					.getContributor().getName());
			resourceClass = (Class<ServerResource>) contributingBundle
					.loadClass(clazz);

			logger.info("Adding resource '" + name + "' (" + id + ")");
			resources.put(id, resourceClass);
		}

		logger.info("Found " + resources.size() + " contributions to "
				+ Activator.PLUGIN_ID + ".resources.");
	}

	public boolean isRestletId(String restletId) {
		return allRestlets.containsKey(restletId);
	}

	public Restlet getRestlet(String restletId) {
		assert (restletId != null);
		return allRestlets.get(restletId).createRestlet();
	}

	public Class<ServerResource> getResource(String resourceId) {
		return resources.get(resourceId);
	}

	public List<Component> getComponents() {
		List<Component> result = new ArrayList<Component>();
		for (ComponentProxy proxy : components.values()) {
			result.add((Component) proxy.createRestlet());
		}
		return result;
	}
}
