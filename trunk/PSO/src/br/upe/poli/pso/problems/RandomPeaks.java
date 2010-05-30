/**
 * 
 */
package br.upe.poli.pso.problems;

import static java.lang.Math.exp;
import br.upe.poli.base.Problem;
/**
 * @author Danilo
 *
 */
public class RandomPeaks extends Problem {
	
	public RandomPeaks(){
		nDimensions = 2;
		init();
	}
	
	private void init() {
		leftBounds = new double[]{0.0, 0.0};
		rightBounds = new double[]{30.0, 30.0};
	}
	
	@Override
	public double getFitness(double[] position) {
		double x = position[0];
		double y = position[1];
		return +5*exp(-0.1*((x-15)*(x-15)+(y-20)*(y-20)))
		-2*exp(-0.08*((x-20)*(x-20)+(y-15)*(y-15)))
		+3*exp(-0.08*((x-25)*(x-25) +(y-10)*(y-10)))
		+2*exp(-0.1*((x-10)*(x-10)+(y-10)*(y-10)))
		-2*exp(-0.5*((x-5)*(x-5)+(y-10)*(y-10)))
		-4*exp(-0.1*((x-15)*(x-15)+(y-5)*(y-5)))
		-2*exp(-0.5*((x-8)*(x-8)+(y-25)*(y-25)))
		-2*exp(-0.5*((x-21)*(x-21)+(y-25)*(y-25)))
		+2*exp(-0.5*((x-25)*(x-25)+(y-16)*(y-16)))
		+2*exp(-0.5*((x-5)*(x-5)+(y-14)*(y-14)));
	}
}