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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;

public class ServerProxy extends IdentifiableRestletProxy {
	private String protocol;
	private int port;

	public void init(IConfigurationElement configElement) throws CoreException {
		super.init(configElement);
		protocol = configElement.getAttribute("protocol");
		port = Integer.valueOf(configElement.getAttribute("port"));
	}

	@Override
	public Restlet createRestlet() {
		return new Server(Protocol.valueOf(protocol), port);
	}

	public String getProtocol() {
		return protocol;
	}

	public int getPort() {
		return port;
	}
}
