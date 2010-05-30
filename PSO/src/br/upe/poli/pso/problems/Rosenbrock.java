package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 5rd Function
 * 
 * Rosenbrock's valley (De Jong's function 2)
 * f2(x)=sum(100·(x(i+1)-x(i)^2)^2+(1-x(i))^2) i=1:n-1; -2.048<=x(i)<=2.048.
 * 
 * global minimum: f(x)=0; x(i)=1, i=1:n.
 */
public class Rosenbrock extends Problem {
	
	public Rosenbrock(){
		nDimensions = 30;
		init();
	}
	
	public Rosenbrock(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -2.048;
			rightBounds[i] = 2.048;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 1; i <= (nDimensions - 1); i++) {
			double xi = position[(i - 1)];
			double yi = position[i];
			sum += (100.0 * ((yi - (xi * xi)) * (yi - (xi * xi)))) + ((1.0 - xi) * (1.0 - xi));
		}
		return sum;
	}
}