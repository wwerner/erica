package net.wolfgangwerner.restlet.registry.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class ApplicationTest {
	@Test
	public void testFirstRouteOnFirstServer() throws IOException {
		URLConnection conn = new URL("http://localhost:8182/application1/1/test1").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("passed test1", result);
	}

	@Test
	public void testSecondRouteOnFirstServer() throws IOException {
		URLConnection conn = new URL("http://localhost:8182/application1/2/test2").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("passed test2", result);
	}
	
	@Test
	public void testFirstRouteOnSecondServer() throws IOException {
		URLConnection conn = new URL("http://localhost:8182/application1/1/test1").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("passed test1", result);
	}

	@Test
	public void testSecondRouteOnSecondServer() throws IOException {
		URLConnection conn = new URL("http://localhost:8182/application1/2/test2").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("passed test2", result);
	}
}
