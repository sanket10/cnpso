package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 6rd Function
 * 
 * Rastrigin's function 6
 * f6(x)=10·n+sum(x(i)^2-10·cos(2·pi·x(i))), i=1:n; -5.12<=x(i)<=5.12.
 * 
 * global minimum: f(x)=0; x(i)=0, i=1:n.
 */
public class Rastrigin extends Problem {
	
	public Rastrigin(){
		nDimensions = 30;
		init();
	}
	
	public Rastrigin(int nDimensions){
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
		double sum = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			double xi = position[i];
			sum += (xi * xi) - (10.0 * Math.cos(2.0 * Math.PI * xi));
		}
		return (10.0 * nDimensions) + sum;
	}
}