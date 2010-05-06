package net.wolfgangwerner.tutorial.restlet;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class GreetingListResource extends ServerResource {

	@Get
	public String list() {
		String key = getMnemonic();
		if(key != null) 
			return GreetingStorage.getInstance().getGreeting(key);
		
		return GreetingStorage.getInstance().getGreetings().toString();
	}

	@Post
	public void set(Representation entity) {
		String key = getMnemonic();
		
		Form form = new Form(entity);
		String greeting = form.getFirstValue("greeting");
		GreetingStorage.getInstance().addGreeting(key, greeting);
		
		setStatus(Status.SUCCESS_OK);
	}

	@Delete
	public void remove() {
		String key = getMnemonic();
		GreetingStorage.getInstance().removeGreeting(key);
	}

	private String getMnemonic() {
		String key = (String) getRequest().getAttributes().get("mnemonic");
		return key;
	}

}
