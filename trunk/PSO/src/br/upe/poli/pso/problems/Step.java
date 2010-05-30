/**
 * 
 */
package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * @author Danilo
 * 
 */
public class Step extends Problem {

	public Step() {
		nDimensions = 30;
		init();
	}
	
	public Step(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -100.0;
			rightBounds[i] = 100.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		double v = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			v = Math.abs(position[i] + 0.5);
			sum += v * v;
		}
		return sum;
	}
}