package br.upe.dsc.pso;

import br.upe.dsc.pso.problems.IProblem;

public class GlobalBestPSO extends PSO {
	
	public GlobalBestPSO(int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, Double C1, Double C2) {

		super(swarmSize, maxIterations, standardDeviation, problem, C1, C2);
	}
	
	@Override
	protected Particle getBestParticleNeighborhood(int index) {
		int indexBestParticle = index;
		Double bestParticleFitness = problem.getFitness(swarm[index].getPBest());
		Double currentParticleFitness;
		
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