package com.redhat.errai.math.client.local;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.EntryPoint;

import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * 
 * @author Andrew Block
 *
 */
@EntryPoint
public class ErraiMath {
	
	
	@Inject
	MathErraiUiForm mathErraiUiForm;
	
	
	@PostConstruct
	public void init()
	{
		RootLayoutPanel.get().add(mathErraiUiForm);	
	}
	


}
