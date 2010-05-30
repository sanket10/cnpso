package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 16rd Function
 * 
 * Six-hump camel back function
 * fSixh(x1,x2)=(4-2.1·x1^2+x1^4/3)·x1^2+x1·x2+(-4+4·x2^2)·x2^2
   -3<=x1<=3, -2<=x2<=2.
 * 
 * global minimum: f(x1,x2)=-1.0316; (x1,x2)=(-0.0898,0.7126), (0.0898,-0.7126).
 */
public class SixHumpCamelBack extends Problem {
	
	public SixHumpCamelBack(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{-3.0, -2.0};
		rightBounds = new double[]{3.0, 2.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return ((4.0 - (2.1*x*x) + ((x*x*x*x)/3.0)) * (x*x)) + (x*y) + ((-4.0 + (4.0*x*x)) * (x*x));
	}
}