package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * EqualPeaksA function
 * 
 * 
 * global minimum: 
 */
public class EqualPeaksA extends Problem {
	
	public EqualPeaksA(){
		nDimensions = 2;
		init();
	}
	
	public EqualPeaksA(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = 0;
			rightBounds[i] = 4.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			double xi = position[i];
			xi = Math.cos(xi);
			sum += (xi * xi);
		}
		return sum;
	}
}