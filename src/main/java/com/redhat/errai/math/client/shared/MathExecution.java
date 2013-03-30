package com.redhat.errai.math.client.shared;

import java.util.Date;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class MathExecution {

	private UserEnvironment userEnvironment;
	private String execution;
	private Date eventDate;
	
	public MathExecution(){}

	public MathExecution(UserEnvironment userEnvironment, String execution, Date eventDate)
	{
		this.setUserEnvironment(userEnvironment);
		this.execution = execution;
		this.setEventDate(eventDate);
	}
	
	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public UserEnvironment getUserEnvironment() {
		return userEnvironment;
	}

	public void setUserEnvironment(UserEnvironment userEnvironment) {
		this.userEnvironment = userEnvironment;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
}
