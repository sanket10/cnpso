package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 1st Function
 * 
 * De Jong's function 1
 * f1(x)=sum(x(i)^2), i=1:n, -5.12<=x(i)<=5.12.
 * 
 * global minimum: f(x)=0, x(i)=0, i=1:n
 */
public class DeJongs extends Problem {
	
	public DeJongs(){
		nDimensions = 2;
		init();
	}
	
	public DeJongs(int nDimensions){
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
			sum += (xi * xi);
		}
		return sum;
	}
}