package net.wolfgangwerner.tutorial.restlet;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class GreetingResource extends ServerResource {

	@Get
	public String represent() {
		return GreetingStorage.getInstance().getText();
	}

	@Put
	public void setGreeting(Representation entity) throws IOException {
		Form form = new Form(entity);
		String greeting = form.getFirstValue("greeting");
		GreetingStorage.getInstance().setText(greeting);
		setStatus(Status.SUCCESS_OK);
	}
}
