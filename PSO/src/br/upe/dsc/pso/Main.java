package br.upe.dsc.pso;

import br.upe.dsc.pso.problemas.IProblem;
import br.upe.dsc.pso.problemas.*;

/**
 * @author marlon
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// IProblem problem = new Problem1();
		// IProblem problem = new Problem2();
		// IProblem problem = new Problem3();
		 IProblem problem = new Problem4();
		// IProblem problem = new Problem5();
		// IProblem problem = new Problem6();

		for (int i = 0; i < 30; i++) {
			PSO pso = new PSO(100,100,0.3, problem, 0.5, 0.8);
			pso.run();
		}
	}

}
