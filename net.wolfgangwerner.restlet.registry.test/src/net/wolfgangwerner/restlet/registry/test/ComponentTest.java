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
