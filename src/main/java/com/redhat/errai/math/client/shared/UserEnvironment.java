package com.redhat.errai.math.client.shared;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class UserEnvironment {
	
	private Manufacturer browser;
	private Manufacturer operationSystem;
	
	public UserEnvironment(Manufacturer operatingSystem, Manufacturer browser)
	{
		this.browser = browser;
		this.operationSystem = operatingSystem;
	}
	
	public UserEnvironment(){}
	
	public Manufacturer getBrowser() {
		return browser;
	}
	public void setBrowser(Manufacturer browser) {
		this.browser = browser;
	}
	public Manufacturer getOperationSystem() {
		return operationSystem;
	}
	public void setOperationSystem(Manufacturer operationSystem) {
		this.operationSystem = operationSystem;
	}

}
