package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 15rd Function
 * 
 * Goldstein-Price's function
 * fGold(x1,x2)=[1+(x1+x2+1)^2·(19-14·x1+3·x1^2-14·x2+6·x1·x2+3·x2^2)]·
              [30+(2·x1-3·x2)^2·(18-32·x1+12·x1^2+48·x2-36·x1·x2+27·x2^2)]
   -2<=x(i)<=2, i=1:2.
 * 
 * global minimum: f(x1,x2)=3; (x1,x2)=(0,-1).
 */
public class GoldsteinPrice extends Problem {
	
	public GoldsteinPrice(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{-2.0, -2.0};
		rightBounds = new double[]{2.0, 2.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return	(1.0 + (((x+y+1.0)*(x+y+1.0)) * (19.0 - (14.0*x) + (3.0*x*x) - (14.0*y) + (6.0*x*y) + (3.0*y*y)))) *
				(30.0 + ((((2.0*x)-(3.0*y))*((2.0*x)-(3.0*y))) * (18.0 - (32.0*x) + (12.0*x*x) + (48.0*y) - (36.0*x*y) + (27.0*y*y))));
	}
}