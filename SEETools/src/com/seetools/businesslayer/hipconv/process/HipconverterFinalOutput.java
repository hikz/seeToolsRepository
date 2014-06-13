package com.seetools.businesslayer.hipconv.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;

@SessionScoped
public class HipconverterFinalOutput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	List<HipconverterOutput> hipconverterOutputList = new ArrayList<HipconverterOutput>();

	public List<HipconverterOutput> getHipconverterOutputList() {
		return hipconverterOutputList;
	}

	public void setHipconverterOutputList(
			List<HipconverterOutput> hipconverterOutputList) {
		this.hipconverterOutputList = hipconverterOutputList;
	}

	public HipconverterFinalOutput() {
		super();
	}	
	
	
}