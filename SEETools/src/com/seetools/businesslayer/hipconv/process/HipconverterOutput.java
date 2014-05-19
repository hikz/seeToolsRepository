package com.seetools.businesslayer.hipconv.process;

import java.io.Serializable;
import java.math.BigDecimal;

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
		this.protonCrossSection = protonCrossSection;
	}
	
	
}
