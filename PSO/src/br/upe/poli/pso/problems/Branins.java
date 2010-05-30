package br.upe.poli.pso.problems;

import br.upe.poli.base.Problem;

/**
 * 13rd Function
 * 
 * Branins's rcos function
 * fBran(x1,x2)=a·(x2-b·x1^2+c·x1-d)^2+e·(1-f)·cos(x1)+e
   a=1, b=5.1/(4·pi^2), c=5/pi, d=6, e=10, f=1/(8·pi)
   -5<=x1<=10, 0<=x2<=15.
 * 
 * global minimum: f(x1,x2)=0.397887; (x1,x2)=(-pi,12.275), (pi,2.275), (9.42478,2.475).
 */
public class Branins extends Problem {
	
	public Branins(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{-5.0, 0.0};
		rightBounds = new double[]{10.0, 15.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double a = 1.0;
		double b = 5.1 / (4.0 * (Math.PI * Math.PI));
		double c = 5.0 / Math.PI;
		double d = 6.0;
		double e = 10.0;
		double f = 1.0 / (8.0 * Math.PI);
		
		double x = position[0];
		double y = position[1];
		
		double aux = (y - (b*(x*x)) + (c*x) - d);
		return (a * (aux * aux)) + (e*(1.0 - f)*Math.cos(x)) + e;
	}
}