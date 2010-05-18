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

public abstract class IdentifiableRestletProxy {
	protected String id;
	protected String name;

	public void init(IConfigurationElement configElement) throws CoreException {
		id = configElement.getAttribute("id");
		name = configElement.getAttribute("name");
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "'" + name + "' (" + id + ")";
	}

	public abstract Restlet createRestlet();
}
