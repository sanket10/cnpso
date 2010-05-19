package br.upe.dsc.pso.algorithm;

import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.view.SwarmObserver;

public class GlobalBestPSO extends PSO {
	
	public GlobalBestPSO(int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, Double C1, Double C2, SwarmObserver swarmObserver) {

		super("Global Best PSO", swarmSize, maxIterations, standardDeviation, problem, C1, C2, swarmObserver);
	}
	
	@Override
	protected Particle getBestParticleNeighborhood(int index) {
		int indexBestParticle = index;
		double bestParticleFitness = problem.getFitness(swarm[index].getPBest());
		double currentParticleFitness;
		
		for (int i = 0; i < swarmSize; i++) {
			currentParticleFitness = problem.getFitness(swarm[i].getPBest());
			
			if (problem.compareFitness(bestParticleFitness, currentParticleFitness)) {
				indexBestParticle = i;
				bestParticleFitness = currentParticleFitness;
			}
		}

		return swarm[indexBestParticle];
	}
	
}