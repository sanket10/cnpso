package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * EqualPeaksA function
 * 
 * 
 * global minimum: 
 */
public class EqualPeaksB extends Problem {
	
	public EqualPeaksB(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = 0;
			rightBounds[i] = 5.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return (Math.cos(x) * Math.cos(x)) + (Math.sin(y) * Math.sin(y));
	}
}