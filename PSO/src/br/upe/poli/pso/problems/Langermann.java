package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 11rd Function
 * 
 * Langermann's function 11
 * f11(x)=-sum(c(i)·(exp(-1/pi·sum((x-A(i))^2))·cos(pi·sum((x-A(i))^2))))
 * i=1:m, 2<=m<=10; 0<=x(i)<=10.
 * 
 * global minimum: f(x)=-1.4 (for m=5); x(i)=???, i=1:n. 
 */
public class Langermann extends Problem {
	
	public Langermann(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = 0.0;
			rightBounds[i] = 10.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double[][] a = new double[][]{{3.0, 5.0, 2.0, 1.0, 7.0}, {5.0, 2.0, 1.0, 4.0, 9.0}};
		double[] c = new double[]{3.0, 5.0, 2.0, 1.0, 7.0};
		double m = 5;
		
		double sum1 = 0.0;
		for (int i = 1; i <= m; i++) {
			double sum2 = 0.0;
			for (int j = 1; j <= nDimensions; j++) {
				double xj = position[(j - 1)];
				sum2 += (xj - a[(j - 1)][(i - 1)]) * (xj - a[(j - 1)][(i - 1)]);
			}
			sum1 += c[(i - 1)] * Math.exp(-1.0/Math.PI * sum2) * Math.cos(Math.PI * sum2);
		}
		return sum1;
	}
}