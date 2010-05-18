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

package net.wolfgangwerner.restlet.registry;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.restlet.Component;

public class Activator implements BundleActivator {
	public static final String PLUGIN_ID = "net.wolfgangwerner.restlet.registry";

	private RestletRegistry registry;

	public void start(BundleContext context) throws Exception {
		registry = RestletRegistry.getInstance();
		registry.readExtensionRegistry();
		for (Component component : registry.getComponents()) {
			component.start();
		}
	}

	public void stop(BundleContext context) throws Exception {
		for (Component component : registry.getComponents()) {
			component.stop();
		}
	}

}
