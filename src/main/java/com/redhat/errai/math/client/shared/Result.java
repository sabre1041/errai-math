package com.redhat.errai.math.client.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Result {

	private double operationResult = 0.0;
	private String statusMessage;
	private int statusCode = 0;

	public double getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(double operationResult) {
		this.operationResult = operationResult;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
