/**
 * 
 */
package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * @author Danilo
 *
 */
public class Quartic extends Problem {
	
	public Quartic() {
		nDimensions = 30;
		init();
	}
	
	public Quartic(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -1.28;
			rightBounds[i] = 1.28;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 1; i <= nDimensions; i++) {
			double xi = position[(i - 1)];
			sum += (i * Math.pow(xi, 4)) + Math.random();
		}
		return sum;
	}
}