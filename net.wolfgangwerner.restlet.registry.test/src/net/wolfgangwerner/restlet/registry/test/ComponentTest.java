package net.wolfgangwerner.restlet.registry.test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class ComponentTest {
	@Test
	public void testHttpServer() throws IOException {
		URL server1Root = new URL("http://localhost:80");
		URLConnection server1Connection = server1Root.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				server1Connection.getInputStream()));

		String result = in.readLine();
		assertEquals("root", result);
	}
	
	@Test
	public void testAlternateHttpServer() throws IOException {
		URL server1Root = new URL("http://localhost:80");
		URLConnection server1Connection = server1Root.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				server1Connection.getInputStream()));

		String result = in.readLine();
		assertEquals("root", result);
	}
}
