package net.wolfgangwerner.restlet.registry.test.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Resource2 extends ServerResource {

	@Get
	public String hello() {
		String param = (String) getRequest().getAttributes().get("param");
		return "passed " + param;
	}
}
