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

package net.wolfgangwerner.restlet.registry.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ComponentTest {
	@Test
	public void testHttpServer() throws IOException {
		assertEquals("resource 1", TestUtil
				.getURL("http://localhost:8182/resource1"));
	}

	@Test
	public void testAlternateHttpServer() throws IOException {
		assertEquals("resource 1", TestUtil
				.getURL("http://localhost:8080/resource1"));
	}
}
