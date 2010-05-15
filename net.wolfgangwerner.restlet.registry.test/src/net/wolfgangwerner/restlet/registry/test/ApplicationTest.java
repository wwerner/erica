package net.wolfgangwerner.restlet.registry.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

public class ApplicationTest {
	@Test
	public void testFirstRouteOnFirstServer() throws IOException {
		assertEquals("passed test1", TestUtil
				.getURL("http://localhost:8182/application1/1/test1"));
	}

	@Test
	public void testSecondRouteOnFirstServer() throws IOException {
		assertEquals("passed test2", TestUtil
				.getURL("http://localhost:8182/application1/2/test2"));
	}

	@Test
	public void testFirstRouteOnSecondServer() throws IOException {
		assertEquals("passed test1", TestUtil
				.getURL("http://localhost:8080/application1/1/test1"));
	}

	@Test
	public void testSecondRouteOnSecondServer() throws IOException {
		assertEquals("passed test2", TestUtil
				.getURL("http://localhost:8080/application1/2/test2"));
	}

	@Test
	public void testApplicationRootResource() throws MalformedURLException,
			IOException {
		assertEquals("resource 1", TestUtil
				.getURL("http://localhost:8182/application2"));
	}
}
