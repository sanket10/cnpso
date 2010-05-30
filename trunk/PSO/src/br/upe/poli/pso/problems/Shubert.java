package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Shubert's function
 *
 * -10 <= xi <= 10, i=1,2
 * 
 * global minimum: f(x) = -186.7309
 */
public class Shubert extends Problem {
	
	public Shubert(){
		nDimensions = 5;
		init();
	}
	
	public Shubert(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -5.12;
			rightBounds[i] = 5.12;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 1; i <= 5; i++) {
			sum1 += i * Math.cos(((i + 1.0) * x) + 1.0);
			sum2 += i * Math.cos(((i + 1.0) * y) + 1.0);
		}
		return sum1 * sum2;
	}
}