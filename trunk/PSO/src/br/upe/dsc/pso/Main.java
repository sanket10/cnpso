package br.upe.dsc.pso;

import br.upe.dsc.pso.problemas.IProblema;
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
//		IProblema problema = new Problema1();
//		IProblema problema = new Problema2();
//		IProblema problema = new Problema3();
//		IProblema problema = new Problema4();
//		IProblema problema = new Problema5();
		IProblema problema = new Problema6();
		
		PSO pso = new PSO(100, problema, 0.4, 0.6);
		pso.rodar();
	}

}
