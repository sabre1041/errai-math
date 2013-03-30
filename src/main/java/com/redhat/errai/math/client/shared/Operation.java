package com.redhat.errai.math.client.shared;


public enum Operation {

	ADD("Add"),
	SUBTRACT("Subtract"),
	MULTIPLY("Multiple"),
	DIVIDE("Divide"),
	EXPONENT("Exponent"),
	FACTORIAL("Factorial"),
	SQRT("Square Root");
	
	private String value;
	
	private Operation(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
}
