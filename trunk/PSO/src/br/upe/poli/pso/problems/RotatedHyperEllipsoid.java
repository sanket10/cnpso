package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 3rd Function
 * 
 * Rotated hyper-ellipsoid function
 * f1b(x)=sum(sum(x(j)^2), j=1:i), i=1:n, -65.536<=x(i)<=65.536.
 * 
 * global minimum: f(x)=0; x(i)= 0, i=1:n.
 */
public class RotatedHyperEllipsoid extends Problem {
	
	public RotatedHyperEllipsoid(){
		nDimensions = 2;
		init();
	}
	
	public RotatedHyperEllipsoid(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -65.536;
			rightBounds[i] = 65.536;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum1 = 0.0;
		for (int i = 1; i <= nDimensions; i++) {
			double sum2 = 0.0;
			for (int j = 1; j <= i; j++) {
				double xi = position[(j - 1)];
				sum2 += xi;
			}
			sum1 += sum2 * sum2;
		}
		return sum1;
	}
}