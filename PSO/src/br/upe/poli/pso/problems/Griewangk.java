package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 8rd Function
 * 
 * Griewangk's function 8
 * f8(x)=sum(x(i)^2/4000)-prod(cos(x(i)/sqrt(i)))+1, i=1:n -600<=x(i)<= 600.
 * 
 * global minimum: f(x)=0; x(i)=0, i=1:n.
 */
public class Griewangk extends Problem {
	
	public Griewangk(){
		nDimensions = 2;
		init();
	}
	
	public Griewangk(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -600.0;
			rightBounds[i] = 600.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		double prod = 1.0;
		for (int i = 1; i <= nDimensions; i++) {
			double xi = position[(i - 1)];
			sum += (xi * xi);
			prod *= Math.cos(xi / Math.sqrt(i));
		}
		return (sum / 4000.0) - prod + 1.0;
	}
}