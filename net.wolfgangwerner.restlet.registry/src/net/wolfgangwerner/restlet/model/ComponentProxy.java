/*******************************************************************************
 * Copyright (c) 2010 Wolfgang Werner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Wolfgang Werner - initial API and implementation and/or initial documentation
 *******************************************************************************/

package net.wolfgangwerner.restlet.model;

import java.util.ArrayList;
import java.util.List;

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.Server;

public class ComponentProxy extends IdentifiableRestletProxy {
	private List<ServerProxy> servers = new ArrayList<ServerProxy>();
	private List<RouteProxy> routes = new ArrayList<RouteProxy>();

	public void init(IConfigurationElement configElement) throws CoreException {
		super.init(configElement);

		for (IConfigurationElement serverConfig : configElement
				.getChildren("server")) {
			ServerProxy serverProxy = new ServerProxy();
			serverProxy.init(serverConfig);
			servers.add(serverProxy);
		}

		for (IConfigurationElement routeConfig : configElement
				.getChildren("route")) {
			routes.add(new RouteProxy(routeConfig));
		}
	}

	@Override
	public Restlet createRestlet() {
		Component component = new Component();

		for (ServerProxy serverProxy : servers) {
			component.getServers().add((Server) serverProxy.createRestlet());
		}

		for (RouteProxy route : routes) {
			if (RestletRegistry.getInstance().isRestletId(route.getTargetId()))
				component.getDefaultHost().attach(
						route.getUrlTemplate(),
						RestletRegistry.getInstance().getRestlet(
								route.getTargetId()));
			else
				component.getDefaultHost().attach(
						route.getUrlTemplate(),
						RestletRegistry.getInstance().getResource(
								route.getTargetId()));
		}

		return component;
	}

	public List<ServerProxy> getServers() {
		return servers;
	}

	public List<RouteProxy> getRoutes() {
		return routes;
	}
}
