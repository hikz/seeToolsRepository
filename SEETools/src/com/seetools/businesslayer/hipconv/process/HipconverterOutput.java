package com.seetools.businesslayer.hipconv.process;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class HipconverterOutput implements Serializable {

	private double energy;
	private double protonCrossSection;
	
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public double getProtonCrossSection() {
		return protonCrossSection;
	}
	public void setProtonCrossSection(double protonCrossSection) {
		
		BigDecimal bigDecimal = new BigDecimal(protonCrossSection);
	    DecimalFormat format = new DecimalFormat("0.##E0");
	    //System.out.println(format.format(bigDecimal));
		this.protonCrossSection = Double.parseDouble(format.format(bigDecimal));
		
	}
	
	
}
