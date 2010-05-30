package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Himmelblau's Function
 * 
 * global minimum: 
 */
public class Himmelblau extends Problem {
	
	public Himmelblau(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{0.0, 0.0};
		rightBounds = new double[]{6.0, 6.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return 200.0 - Math.pow((x*x + y - 11.0), 2) - Math.pow((x + y*y - 7.0), 2);
	}
}