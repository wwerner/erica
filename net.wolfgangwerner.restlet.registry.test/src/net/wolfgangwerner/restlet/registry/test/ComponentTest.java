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
		URLConnection conn = new URL("http://localhost:8182/resource1").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("resource 1", result);
	}

	@Test
	public void testAlternateHttpServer() throws IOException {
		URLConnection conn = new URL("http://localhost:8080/resource1").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("resource 1", result);
	}
}
