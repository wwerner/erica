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

public class RouterTest {
	@Test
	public void testFirstRouteOnFirstServer() throws IOException {
		assertEquals("passed test1", TestUtil
				.getURL("http://localhost:8182/router1/1/test1"));
	}

	@Test
	public void testSecondRouteOnFirstServer() throws IOException {
		assertEquals("passed test2", TestUtil
				.getURL("http://localhost:8182/router1/2/test2"));
	}

	@Test
	public void testFirstRouteOnSecondServer() throws IOException {
		assertEquals("passed test1", TestUtil
				.getURL("http://localhost:8182/router1/1/test1"));
	}

	@Test
	public void testSecondRouteOnSecondServer() throws IOException {
		assertEquals("passed test2", TestUtil
				.getURL("http://localhost:8182/router1/2/test2"));
	}
}
