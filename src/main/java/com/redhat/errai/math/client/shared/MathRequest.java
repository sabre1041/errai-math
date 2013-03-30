package com.redhat.errai.math.client.shared;

import org.jboss.errai.databinding.client.api.Bindable;

@Bindable
public class MathRequest {
	
	private String lhs;
	private String rhs;
	
	public String getLhs() {
		return lhs;
	}
	public void setLhs(String lhs) {
		this.lhs = lhs;
	}
	public String getRhs() {
		return rhs;
	}
	public void setRhs(String rhs) {
		this.rhs = rhs;
	}

}
