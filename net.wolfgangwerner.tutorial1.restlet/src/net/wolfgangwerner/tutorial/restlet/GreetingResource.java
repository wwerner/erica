package net.wolfgangwerner.tutorial.restlet;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class GreetingResource extends ServerResource {

	@Get
	public String represent() {
		return GreetingStorage.getInstance().getText();
	}
}
