package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Plateaus' Function
 * 
 * global minimum: 
 */
public class Plateaus extends Problem {
	
	public Plateaus(){
		nDimensions = 2;
		init();
	}
	
	public Plateaus(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -2.0*Math.PI;
			rightBounds[i] = 2.0*Math.PI;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			double xi = position[i];
			sum += Math.cos(xi);
		}
		return Math.signum(sum);
	}
}