package com.redhat.errai.math.client.local;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.enterprise.client.jaxrs.api.ResponseException;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.redhat.errai.math.client.shared.MathExecution;
import com.redhat.errai.math.client.shared.MathRequest;
import com.redhat.errai.math.client.shared.MathService;
import com.redhat.errai.math.client.shared.Operation;
import com.redhat.errai.math.client.shared.Result;


/**
 * 
 * @author Andrew Block
 *
 */
@Templated("#mathPanel")
public class MathErraiUiForm extends Composite {

	
	@Inject
	@DataField
	ListBox operations;
	
	@Inject
	@DataField
	Button submitMath;
	
	@Inject
	@AutoBound
	DataBinder<MathRequest> request;
	
	@Inject
	@Bound
	@DataField
	@SuppressWarnings("unused")
	private TextBox lhs;
	
	@Inject
	@Bound
	@DataField
	@SuppressWarnings("unused")
	private TextBox rhs;
	
	@Inject
	@DataField
	private VerticalPanel lhsPanel;
	
	@Inject
	@DataField
	private VerticalPanel rhsPanel;
	
	@Inject
	@DataField
	private Label rhsLabel;

	@Inject
	@DataField
	private Label lhsLabel;
	
	@Inject
	@DataField
	private Label resultLabel;
	
	@Inject
	@DataField
	private CalculationStatisticsWidget calculationStatisticsWidget;	
	
	private NumberFormat format = NumberFormat.getDecimalFormat();
	
	@Inject
	Caller<MathService> mathService;
	
	private ListDataProvider<MathExecution> listDataProvider;
	

	@PostConstruct
	public void init()
	{
		rhsPanel.add(rhsLabel);
		rhsPanel.add(rhs);
		
		lhsPanel.add(lhsLabel);
		lhsPanel.add(lhs);
		
		for(Operation operation : Operation.values())
		{
			operations.addItem(operation.getValue(), operation.toString());
		}
				
	}
	
	@EventHandler("submitMath")
	public void submitMath(ClickEvent e)
	{
		switch(Operation.valueOf(operations.getValue(operations.getSelectedIndex())))
		{
		case ADD:
			mathService.call(resultCallback, errorCallback).add(request.getModel().getLhs(), request.getModel().getRhs(), "1");
			break;
		case SUBTRACT:
			mathService.call(resultCallback, errorCallback).subtract(request.getModel().getLhs(), request.getModel().getRhs(), "1");
			break;
		case MULTIPLY:
			mathService.call(resultCallback, errorCallback).multiply(request.getModel().getLhs(), request.getModel().getRhs(), "1");
			break;
		case DIVIDE:
			mathService.call(resultCallback, errorCallback).divide(request.getModel().getLhs(), request.getModel().getRhs(), "1");
			break;
		case EXPONENT:
			mathService.call(resultCallback, errorCallback).exponent(request.getModel().getLhs(), request.getModel().getRhs(), "1");
			break;
		case FACTORIAL:
			mathService.call(resultCallback, errorCallback).factorial(request.getModel().getLhs(), "1");
			break;
		case SQRT:
			mathService.call(resultCallback, errorCallback).squareRoot(request.getModel().getLhs(), "1");
			break;
		}

	}
	
	@EventHandler("operations")
	void onOperationChange(ChangeEvent e)
	{
		switch(Operation.valueOf(operations.getValue(operations.getSelectedIndex())))
		{
		case ADD:
			showBothTextBoxes(true);
			break;
		case SUBTRACT:
			showBothTextBoxes(true);
			break;
		case MULTIPLY:
			showBothTextBoxes(true);
			break;
		case DIVIDE:
			showBothTextBoxes(true);
			break;
		case EXPONENT:
			showBothTextBoxes(false);
			break;
		case FACTORIAL:
			showBothTextBoxes(true);
			break;
		case SQRT:
			showBothTextBoxes(false);
			break;
		}
	}
	
	
	private void showBothTextBoxes(boolean value)
	{
		
		if(value)
		{
			lhsLabel.setText("First Value");
			rhsPanel.setVisible(true);
		}
		else
		{
			lhsLabel.setText("Value");
			rhsPanel.setVisible(false);	
		}
		
	}
	
	final RemoteCallback<Result> resultCallback = new RemoteCallback<Result>(){

		@Override
		public void callback(Result response) {
			if(response.getStatusCode()==0)
			{
				String result = "Result: "+ format.format(response.getOperationResult());
				resultLabel.setText(result);
				
			}
			else
			{
				resultLabel.setText("Error: " + response.getStatusMessage());
			}
		}
		
	};
	
	final ErrorCallback errorCallback = new ErrorCallback() {

		@Override
		public boolean error(Message message, Throwable throwable) {
			try {
			      throw throwable;
			    }
			    catch (ResponseException e) {
			      Response response = e.getResponse();
			      response.getStatusCode();
			    }
			    catch (Throwable t) {
			    }
			    return false;
			  }
		
	};

		
}
