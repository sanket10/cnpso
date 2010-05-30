package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * "Drop wave" function
 * 
 * global minimum: 
 */
public class DropWave extends Problem {
	
	public DropWave(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{-5.12, -5.12};
		rightBounds = new double[]{5.12, 5.12};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return -((1.0 + Math.cos(12.0 * Math.sqrt((x*x) + (y*y)))) / ((((x*x) + (y*y)) / 2.0) + 2.0));
	}
}