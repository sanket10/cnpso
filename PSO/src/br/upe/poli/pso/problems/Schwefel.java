package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 7rd Function
 * 
 * Schwefel's function 7
 * f7(x)=sum(-x(i)·sin(sqrt(abs(x(i))))), i=1:n; -500<=x(i)<=500.
 * 
 * global minimum: f(x)=-n·418.9829; x(i)=420.9687, i=1:n.
 */
public class Schwefel extends Problem {
	
	public Schwefel(){
		nDimensions = 30;
		init();
	}
	
	public Schwefel(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -500.0;
			rightBounds[i] = 500.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 0; i < nDimensions; i++) {
			double xi = position[i];
			sum += -xi * Math.sin(Math.sqrt(Math.abs(xi)));
		}
		return sum;
	}
}