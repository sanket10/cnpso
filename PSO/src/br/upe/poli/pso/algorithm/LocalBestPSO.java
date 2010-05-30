package br.upe.poli.pso.algorithm;

import br.upe.poli.base.Problem;
import br.upe.poli.base.SwarmObserver;

public class LocalBestPSO extends PSO {

	public LocalBestPSO(int swarmSize, int maxIterations, double standardDeviation,
			Problem problem, double C1, double C2, SwarmObserver swarmObserver) {
		super("Local Best PSO", swarmSize, maxIterations, standardDeviation, problem, C1, C2, swarmObserver);
	}
	
	public LocalBestPSO(String name, int swarmSize, int maxIterations, double standardDeviation,
			double C1, double C2) {
		super(name, swarmSize, maxIterations, standardDeviation, null, C1, C2, null);
	}
	
	@Override
	protected Particle getBestParticleNeighborhood(int index) {
		int indexBestParticle = index;
		int indexLeftNeighbor = (index > 0) ? index - 1 : swarmSize - 1;
		int indexRightNeighbor = (index < swarmSize - 1) ? index + 1 : 0;
		
		double gBestFitness = swarm[index].getPBestFitness();
		double leftNeighborParticlePBestFitness = this.swarm[indexLeftNeighbor].getPBestFitness();
		double rightNeighborParticlePBestFitness = this.swarm[indexRightNeighbor].getPBestFitness();
		
		if (leftNeighborParticlePBestFitness < gBestFitness) {
			indexBestParticle = indexLeftNeighbor;
			gBestFitness = leftNeighborParticlePBestFitness;
		}
		if (rightNeighborParticlePBestFitness < gBestFitness) {
			indexBestParticle = indexRightNeighbor;
			gBestFitness = rightNeighborParticlePBestFitness;
		}
		return swarm[indexBestParticle];
	}
}