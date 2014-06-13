package com.seetools.businesslayer.hipconv.process;

import java.io.Serializable;

public class HipconverterInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double xsec;
	private double let;
	private double energy;
	
	public double getXsec() {
		return xsec;
	}
	public void setXsec(double xsec) {
		this.xsec = xsec;
	}
	
	public double getLet() {
		return let;
	}
	public void setLet(double let) {
		this.let = let;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public HipconverterInput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
		
}
