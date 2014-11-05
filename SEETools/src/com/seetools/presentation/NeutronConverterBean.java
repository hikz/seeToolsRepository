package com.seetools.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.neutron.business.BurstGenerationRateCalculationMethod;
import com.neutron.business.InputVariables;
import com.neutron.business.SimpleCalculationMethod;

/**
 * The Class NeutronConverterBean.
 */
@ManagedBean(name = "neutronConverterBean")
@ViewScoped
public class NeutronConverterBean {

	/**
	 * Instantiates a new neutron converter bean.
	 */
	public NeutronConverterBean() {
		inputVariables = new InputVariables();
	}

	/** The input variables. */
	private InputVariables inputVariables;

	/** The part number. */
	private String partNumber;

	/** The error rate. */
	private double errorRate;

	/**
	 * Gets the error rate.
	 *
	 * @return the error rate
	 */
	public double getErrorRate() {
		return errorRate;
	}

	/**
	 * Sets the error rate.
	 *
	 * @param errorRate
	 *            the new error rate
	 */
	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}

	/**
	 * Gets the part number.
	 *
	 * @return the part number
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * Sets the part number.
	 *
	 * @param partNumber
	 *            the new part number
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * Gets the input variables.
	 *
	 * @return the input variables
	 */
	public InputVariables getInputVariables() {
		return inputVariables;
	}

	/**
	 * Sets the input variables.
	 *
	 * @param inputVariables
	 *            the new input variables
	 */
	public void setInputVariables(InputVariables inputVariables) {
		this.inputVariables = inputVariables;
	}

	/**
	 * Calculate.
	 */
	public void calculate() {
		SimpleCalculationMethod method = new SimpleCalculationMethod();
		errorRate = method.calculate(inputVariables);

	}

	public void calculate1() {
		BurstGenerationRateCalculationMethod bgrcm = new BurstGenerationRateCalculationMethod(null);
		errorRate = bgrcm.calculate(inputVariables);
	}
}
