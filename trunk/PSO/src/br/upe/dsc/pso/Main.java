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
		// IProblema problem = new Problema1();
		// IProblema problem = new Problema2();
		// IProblema problem = new Problema3();
		// IProblema problem = new Problema4();
		IProblem problem = new Problem5();
		// IProblema problema = new Problema6();

		for (int i = 0; i < 30; i++) {
			PSO pso = new PSO(100,100,0.3, problem, 0.5, 0.8);
			pso.run();
		}
	}

}
