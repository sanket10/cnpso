package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 10rd Function
 * 
 * Ackley's Path function 10
 * f10(x)=-a·exp(-b·sqrt(1/n·sum(x(i)^2)))-exp(1/n·sum(cos(c·x(i))))+a+exp(1)
 * a=20; b=0.2; c=2·pi; i=1:n; -32.768<=x(i)<=32.768.
 * 
 * global minimum: f(x)=0; x(i)=0, i=1:n.
 */
public class Ackley extends Problem {
	
	public Ackley(){
		nDimensions = 30;
		init();
	}
	
	public Ackley(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -32.768;
			rightBounds[i] = 32.768;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double a = 20.0, b = 0.2, c = 2.0 * Math.PI;
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			double xi = position[i];
			sum1 += (xi * xi);
			sum2 += Math.cos(c * xi);
		}
		return (-a * Math.exp(-b * Math.sqrt(sum1 / nDimensions))) - Math.exp(sum2 / nDimensions) + a + Math.E;
	}
}