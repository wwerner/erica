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

import net.wolfgangwerner.restlet.registry.RestletRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Application;
import org.restlet.Restlet;

public class ApplicationProxy extends IdentifiableRestletProxy {
	private Application application;
	private String inboundRootReference;

	public void init(IConfigurationElement configElement) throws CoreException {
		super.init(configElement);
		if (configElement.getAttribute("class") != null) {
			application = (Application) configElement
					.createExecutableExtension("class");
		}
		inboundRootReference = configElement.getAttribute("inboundRoot");

	}

	@Override
	public Restlet createRestlet() {
		if (application == null)
			application = new Application();
		else
			return application;

		if (RestletRegistry.getInstance().isRestletId(inboundRootReference))
			application.setInboundRoot(RestletRegistry.getInstance()
					.getRestlet(inboundRootReference));
		else
			application.setInboundRoot(RestletRegistry.getInstance()
					.getResource(inboundRootReference));

		return application;
	}

	public String getInboundRootReference() {
		return inboundRootReference;
	}

}
