package br.upe.poli.pso.algorithm;

import br.upe.poli.base.Problem;
import br.upe.poli.base.SwarmObserver;

public class GlobalBestPSO extends PSO {
	
	public GlobalBestPSO(int swarmSize, int maxIterations, double standardDeviation,
			Problem problem, double C1, double C2, SwarmObserver swarmObserver) {

		super("Global Best PSO", swarmSize, maxIterations, standardDeviation, problem, C1, C2, swarmObserver);
	}
	
	public GlobalBestPSO(String name, int swarmSize, int maxIterations, double standardDeviation,
			double C1, double C2) {
		super(name, swarmSize, maxIterations, standardDeviation, null, C1, C2, null);
	}
	
	@Override
	protected Particle getBestParticleNeighborhood(int index) {
		return gBestParticle;
	}
}