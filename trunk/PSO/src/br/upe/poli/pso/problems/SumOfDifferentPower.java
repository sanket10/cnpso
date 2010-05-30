package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 9rd Function
 * 
 * Sum of different power function 9
 * f9(x)=sum(abs(x(i))^(i+1)), i=1:n; -1<=x(i)<=1.
 * 
 * global minimum: f(x)=0; x(i)=0, i=1:n.
 */
public class SumOfDifferentPower extends Problem {
	
	public SumOfDifferentPower(){
		nDimensions = 2;
		init();
	}
	
	public SumOfDifferentPower(int nDimensions){
		this.nDimensions = nDimensions;
		init();
	}
	
	private void init() {
		leftBounds = new double[nDimensions];
		rightBounds = new double[nDimensions];
		for (int i = 0; i < nDimensions; i++) {
			leftBounds[i] = -1.0;
			rightBounds[i] = 1.0;
		}
	}
	
	@Override
	public double getFitness(double[] position) {
		double sum = 0.0;
		for (int i = 1; i <= nDimensions; i++) {
			double xi = position[(i - 1)];
			double xiAbs = Math.abs(xi);
			double pow = 1.0;
			for (int j = 1; j <= (i + 1); j++) {
				pow *= xiAbs;
			}
			sum += pow;
		}
		return sum;
	}
}