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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestUtil {
	public static String getURL(String url) throws IOException, MalformedURLException {
		URLConnection conn = new URL(url).openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		return result;
	}
}
