package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Circles' function
 * 
 * 
 * global minimum: 
 */
public class Circles extends Problem {
	
	public Circles(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{-10.0, -10.0};
		rightBounds = new double[]{10.0, 10.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		
		return Math.pow((x*x + y*y), 0.25)*(Math.pow(Math.sin(50.0*Math.pow((x*x + y*y), 0.1)), 2.0) + 1.0);
	}
}