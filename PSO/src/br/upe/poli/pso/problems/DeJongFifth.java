package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * Fifth function of De Jong
 * 
 * global minimum: 
 */
public class DeJongFifth extends Problem {
	
	public DeJongFifth(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{-65.536, -65.536};
		rightBounds = new double[]{65.536, 65.536};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		double sum1 = 0.0;
		for (int i = -2; i <= 2; i++) {
			double sum2 = 0.0;
			for (int j = -2; j <= 2; j++) {
				sum2 += 1.0 / ((5.0 * (i + 2.0)) + j + 3.0 + Math.pow(x - (16.0 * j), 6) + Math.pow(y - (16.0 * i), 6));
			}
			sum1 += sum2;
		}
		return 1.0 / (0.002 + sum1);
	}
}