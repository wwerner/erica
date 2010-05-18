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

import org.eclipse.core.runtime.IConfigurationElement;

public class RouteProxy {
	private String urlTemplate;
	private String targetId;

	public RouteProxy(IConfigurationElement configElement) {
		urlTemplate = configElement.getAttribute("urlTemplate");
		targetId = configElement.getAttribute("targetId");
	}

	public String getUrlTemplate() {
		return urlTemplate;
	}

	public String getTargetId() {
		return targetId;
	}
}
