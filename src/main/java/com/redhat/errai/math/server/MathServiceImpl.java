package com.redhat.errai.math.server;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.math3.util.ArithmeticUtils;

import com.redhat.errai.math.client.shared.Calculation;
import com.redhat.errai.math.client.shared.MathExecution;
import com.redhat.errai.math.client.shared.MathService;
import com.redhat.errai.math.client.shared.Operation;
import com.redhat.errai.math.client.shared.Result;
import com.redhat.errai.math.client.shared.UserEnvironment;

/**
 * 
 * @author Andrew Block
 *
 */
@ApplicationScoped
public class MathServiceImpl implements MathService {
	

	private static final String DEFAULT_VALUE = "0.0";
	
	public MathServiceImpl() {
	}

	
	@Inject @Calculation
	Event<MathExecution> mathExecutionResponseEvent;
	
	
	@Override
	public Result add(String lhs, String rhs, String userAgent) {
		return performOperation(lhs, rhs, Operation.ADD, userAgent);
	}

	@Override
	public Result subtract(String lhs, String rhs, String userAgent) {
		return performOperation(lhs, rhs, Operation.SUBTRACT, userAgent);
	}

	@Override
	public Result multiply(String lhs, String rhs, String userAgent) {
		return performOperation(lhs, rhs, Operation.MULTIPLY, userAgent);
	}

	@Override
	public Result divide(String lhs, String rhs, String userAgent) {
		return performOperation(lhs, rhs, Operation.DIVIDE, userAgent);
	}

	@Override
	public Result exponent(String base, String power, String userAgent) {
		return performOperation(base, power, Operation.EXPONENT, userAgent);
	}

	@Override
	public Result squareRoot(String num, String userAgent) {
		return performOperation(num, DEFAULT_VALUE, Operation.SQRT, userAgent);
	}

	@Override
	public Result factorial(String num, String userAgent) {
		return performOperation(num, DEFAULT_VALUE, Operation.FACTORIAL, userAgent);
	}

	public Result performOperation(String lhs, String rhs, Operation operation, String userAgent) {
		String message = "";
		String mathExecutionMessage = "";
		double result = 0.0;
		int code = 0;

		try {
			double leftNumber = Double.parseDouble(lhs);
			double rightNumber = Double.parseDouble(rhs);

			switch(operation)
			{
			case ADD:
				mathExecutionMessage = leftNumber + " + " + rightNumber;
				result = leftNumber + rightNumber;
				break;
			case SUBTRACT:
				mathExecutionMessage = leftNumber + " - " + rightNumber;
				result = leftNumber - rightNumber;
				break;
			case MULTIPLY:
				mathExecutionMessage = leftNumber + " * " + rightNumber;
				result = leftNumber * rightNumber;
				break;
			case DIVIDE:
				if(rightNumber == 0.0)
				{
					throw new Exception("Cannot Divide by 0");
				}
				
				mathExecutionMessage = leftNumber + " / " + rightNumber;
				result = leftNumber / rightNumber;
				break;
			case EXPONENT:
				mathExecutionMessage = leftNumber + " ^ " + rightNumber;
				result = Math.pow(leftNumber, rightNumber);
				break;
			case SQRT:
				mathExecutionMessage = "SQRT " + leftNumber;
				result = Math.sqrt(leftNumber);	
				break;
			case FACTORIAL:
				mathExecutionMessage = leftNumber + "!";
				int num = Integer.parseInt(lhs);
				result = ArithmeticUtils.factorialDouble(num);
				break;
			default:
				throw new java.lang.Exception("Invalid operation specified: "
						+ operation);
			}
			
			message = "Success";
			
			if(mathExecutionResponseEvent != null)
			{
				sendMathExecutionEvent(mathExecutionMessage, userAgent);
			}

						
		} catch (Exception e) {
			message = "There was an error in the operation";
			code = 1;
		} 

		Result res = new Result();
		res.setStatusMessage(message);
		res.setOperationResult(result);
		res.setStatusCode(code);
		
		return res;
	}
	
	private void sendMathExecutionEvent(String mathExecutionMessage, String userAgentString)
	{
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
		
		nl.bitwalker.useragentutils.Manufacturer browserManufacturer = userAgent.getBrowser().getManufacturer();
		nl.bitwalker.useragentutils.Manufacturer operatingSystemManufacturer = userAgent.getOperatingSystem().getManufacturer();
		
		UserEnvironment userEnvironment = new UserEnvironment(ManufacturerFactory.getManufacturer(operatingSystemManufacturer), ManufacturerFactory.getManufacturer(browserManufacturer));
	
		mathExecutionResponseEvent.fire(new MathExecution(userEnvironment, mathExecutionMessage, new Date()));

	}
}
