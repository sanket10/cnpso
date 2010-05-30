package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Peaks' Function
 * 
 * global minimum: 
 */
public class Peaks extends Problem {
	
	public Peaks(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{0.0, 0.0};
		rightBounds = new double[]{3.0, 3.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return	3.0*Math.pow((1.0-x), 2)*Math.exp(-(x*x+Math.pow((y+1.0), 2)))
				- 10.0*((x/5.0) -Math.pow(x, 3)- Math.pow(y, 5))*Math.exp(-(x*x + y*y))
				- (1.0/3.0)*Math.exp(-(Math.pow(x+1.0, 2) + y*y));
	}
}