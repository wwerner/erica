package net.wolfgangwerner.restlet.model;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class RestletProxy {
	Bundle contributingBundle;
	protected String id;

	public RestletProxy(IConfigurationElement configElement) {
		Platform.getBundle(configElement.getContributor().getName());
	}

	public Bundle getContributingBundle() {
		return contributingBundle;
	}
}
