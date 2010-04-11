package net.wolfgangwerner.tutorial.restlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GreetingStorage {
	private static GreetingStorage instance = new GreetingStorage();
	private String currentGreetingKey = "default";
	private Map<String, String> greetings = new ConcurrentHashMap<String, String>();

	public GreetingStorage() {
		super();
		greetings
				.put("default",
						"Enterprise, what we got back didn't live long... fortunately.");
	}

	public static GreetingStorage getInstance() {
		return instance;
	}

	public void addGreeting(String key, String text) {
		greetings.put(key, text);
	}

	public void removeGreeting(String key) {
		greetings.remove(key);
	}

	public String getGreeting(String key) {
		return greetings.get(key);
	}

	public void setCurrent(String key) {
		currentGreetingKey = key;
	}

	public String getText() {
		return getGreeting(currentGreetingKey);
	}

	public Map<String, String> getGreetings() {
		return greetings;
	}
}
