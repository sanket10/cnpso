package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 2nd Function
 * 
 * Axis parallel hyper-ellipsoid function
 * f1a(x)=sum(i * x(i)^2), i=1:n, -5.12<=x(i)<=5.12.
 * 
 * global minimum: f(x)=0; x(i)= 0, i=1:n.
 */
public class AxisParallelHyperEllipsoid extends Problem {
	
	public AxisParallelHyperEllipsoid(){
		nDimensions = 2;
		init();
	}
	
	public AxisParallelHyperEllipsoid(int nDimensions){
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
		for (int i = 1; i <= nDimensions; i++) {
			double xi = position[(i - 1)];
			sum += i * (xi * xi);
		}
		return sum;
	}
}