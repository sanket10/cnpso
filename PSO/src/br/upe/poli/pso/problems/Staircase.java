package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Staircase Function
 * 
 * global minimum: 
 */
public class Staircase extends Problem {
	
	public Staircase(){
		nDimensions = 2;
		init();
	}
	
	public Staircase(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -2.0;
			rightBounds[i] = 2.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			double xi = position[i];
			sum -= Math.floor(xi);
		}
		return (5.0 * nDimensions) - sum;
	}
}