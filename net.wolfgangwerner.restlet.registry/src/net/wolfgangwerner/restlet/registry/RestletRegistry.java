package net.wolfgangwerner.restlet.registry;

import java.util.Map;

import org.restlet.resource.ServerResource;

public class RestletRegistry {
	private Map<String, Component> components;
	private Map<String, Class<ServerResource>> resources;
}
