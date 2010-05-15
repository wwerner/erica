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
