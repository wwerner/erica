package net.wolfgangwerner.restlet.registry.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class RouterTest {
	@Test
	public void testFirstRoute() throws IOException {
		URLConnection conn = new URL("http://localhost:80/a/1/test1").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("passed test1", result);
	}

	@Test
	public void testSecondRoute() throws IOException {
		URLConnection conn = new URL("http://localhost:80/a/2/test2").openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String result = in.readLine();
		
		assertEquals("passed test2", result);
	}
}
