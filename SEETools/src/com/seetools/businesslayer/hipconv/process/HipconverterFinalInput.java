package com.seetools.businesslayer.hipconv.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HipconverterFinalInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<HipconverterInput> hipconverterInputList = new ArrayList<HipconverterInput>();
	private int numberOfBits;
	
	public List<HipconverterInput> getHipconverterInputList() {
		return hipconverterInputList;
	}

	public void setHipconverterInputList(List<HipconverterInput> hipconverterInputList) {
		this.hipconverterInputList = hipconverterInputList;
	}

	public int getNumberOfBits() {
		return numberOfBits;
	}

	public void setNumberOfBits(int numberOfBits) {
		this.numberOfBits = numberOfBits;
	}	
}
