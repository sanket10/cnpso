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
		// IProblema problema = new Problema1();
		// IProblema problema = new Problema2();
		// IProblema problema = new Problema3();
		// IProblema problema = new Problema4();
		IProblem problema = new Problema5();
		// IProblema problema = new Problema6();

		for (int i = 0; i < 30; i++) {
			PSO pso = new PSO(100,100,0.3, problema, 0.5, 0.8);
			pso.run();
		}
	}

}
