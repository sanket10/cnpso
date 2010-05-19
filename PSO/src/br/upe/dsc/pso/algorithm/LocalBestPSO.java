package br.upe.dsc.pso.algorithm;

import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.view.SwarmObserver;

public class LocalBestPSO extends PSO {

	public LocalBestPSO(int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, Double C1, Double C2, SwarmObserver swarmObserver) {
		
		super("Local Best PSO", swarmSize, maxIterations, standardDeviation, problem, C1, C2, swarmObserver);
	}

	@Override
	protected Particle getBestParticleNeighborhood(int index) {
		int indexBestParticle = index;
		int indexLeftNeighbor = (index > 0) ? index - 1
				: swarmSize - 1;
		int indexRightNeighbor = (index < swarmSize - 1) ? index + 1
				: 0;
		double best = 0.0;

		double currentParticlePBestFitness = this.problem
				.getFitness(this.swarm[index].getPBest());
		double leftNeighborParticlePBestFitness = this.problem
				.getFitness(this.swarm[indexLeftNeighbor].getPBest());
		double rightNeighborParticlePBestFitness = this.problem
				.getFitness(this.swarm[indexRightNeighbor].getPBest());

		best = currentParticlePBestFitness;

		if (this.problem.compareFitness(best,
				leftNeighborParticlePBestFitness)) {
			indexBestParticle = indexLeftNeighbor;
			best = leftNeighborParticlePBestFitness;
		}

		if (this.problem.compareFitness(best,
				rightNeighborParticlePBestFitness)) {
			indexBestParticle = indexRightNeighbor;
		}

		return swarm[indexBestParticle];
	}
}