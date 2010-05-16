package br.upe.dsc.pso;

import br.upe.dsc.pso.algorithm.LocalBestPSO;
import br.upe.dsc.pso.problems.*;
import br.upe.dsc.pso.view.SwarmObserver;

/**
 * @author marlon
 * 
 */
public class Main {

	public static void main(String[] args) {

//		IProblem problem = new Problem1();
//		IProblem problem = new Problem2();
//		IProblem problem = new Problem3();
//		IProblem problem = new Problem4();
//		IProblem problem = new Problem5();
//		IProblem problem = new Problem6();
		IProblem problem = new BookProblem();

		int swarmSize = 100;
		
		for (int i = 0; i < 30; i++) {
//			GlobalBestPSO pso = new GlobalBestPSO(swarmSize,100,0.3, problem, 0.5, 0.8, new SwarmObserver(swarmSize, problem));
			LocalBestPSO pso = new LocalBestPSO(swarmSize,100,0.3, problem, 0.5, 0.8, new SwarmObserver(swarmSize, problem));
			pso.run();
		}
	}

}
