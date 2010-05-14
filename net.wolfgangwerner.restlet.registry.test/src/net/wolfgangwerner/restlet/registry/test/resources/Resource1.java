package net.wolfgangwerner.restlet.registry.test.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Resource1 extends ServerResource {

	@Get
	public String hello(){
		return "resource 1";
	}
}
