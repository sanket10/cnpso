package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problemas.IProblem;

public class PSO {
	private final double INITIAL_WEIGHT = 0.9;
	private final double FINAL_WEIGHT = 0.4;
	
	private int dimensions;
	private int swarmSize;
	private Particle swarm[];
	private Double[] gBest;
	private Double C1;
	private Double C2;
	private IProblem problem;
	
	// Current inertia factor
	private double inertiaWeight;
	private double iteration;
	private double maxIterations;
	private double standardDeviation;
	private double[] allFitness;

	public PSO(int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, Double C1, Double C2) {

		this.dimensions = problem.getDimensionsNumber();
		this.swarmSize = swarmSize;
		this.swarm = new Particle[swarmSize];
		this.allFitness = new double[swarmSize];
		this.gBest = getZero();
		this.problem = problem;
		this.C1 = C1;
		this.C2 = C2;
		this.inertiaWeight = INITIAL_WEIGHT;
		this.maxIterations = maxIterations;
		this.standardDeviation = standardDeviation;
	}

	public void run() {
		init();
		double standardDeviation;
		for (int i = 0; i < this.maxIterations; i++) {
			iteration = i;
			iterate();

			standardDeviation = Statistics.getStandardDeviation(allFitness);
			System.out.println("WEIGTH: " + inertiaWeight);
			if (standardDeviation < this.standardDeviation)
				break;
		}

		System.out.println("Best position: " + problem.getFitness(gBest));
	}

	private void init() {
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = new Particle(dimensions);
			particle.setCurrentPosition(getInitialPosition());
			particle.setPBest(particle.getCurrentPosition());
			particle.setVelocity(getZero());
			swarm[i] = particle;
		}
	}

	private void iterate() {
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];

			// stores the better particle's position.
			this.allFitness[i] = calculatePBest(particle);
			calculateGBest(particle);
		}

		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];

			updateParticleVelocity(particle, i);
			particle.updateCurrentPosition(problem);
		}
	}

	private void updateParticleVelocity(Particle currentParticle, int index) {
		Particle bestParticleNeighborhood;

		bestParticleNeighborhood = swarm[getBestParticleNeighborhood(index)];
		currentParticle.updateVelocity(inertiaWeight,
				bestParticleNeighborhood.getPBest(), C1, C2);
		
		double percentcomplete = (iteration / maxIterations);
		inertiaWeight -= inertiaWeight*percentcomplete;
		
		if(inertiaWeight < FINAL_WEIGHT) 
			inertiaWeight = FINAL_WEIGHT;
	}

	private int getBestParticleNeighborhood(int index) {
		int indexBestParticle = index;
		int indexLeftNeighbor = (index > 0) ? index - 1
				: swarmSize - 1;
		int indexRightNeighbor = (index < swarmSize - 1) ? index + 1
				: 0;
		double best = 0.0;

		Double currentParticlePBestFitness = this.problem
				.getFitness(this.swarm[index].getPBest());
		Double leftNeighborParticlePBestFitness = this.problem
				.getFitness(this.swarm[indexLeftNeighbor].getPBest());
		Double rightNeighborParticlePBestFitness = this.problem
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

		return indexBestParticle;
	}

	private double calculatePBest(Particle particle) {
		Double[] currentPosition = particle.getCurrentPosition();
		Double[] pBest = particle.getPBest();

		Double currentPositionFitness = this.problem.getFitness(currentPosition);
		Double pBestFitness = this.problem.getFitness(pBest);

		if (this.problem.compareFitness(pBestFitness, currentPositionFitness)) {
			particle.setPBest(currentPosition);
			return currentPositionFitness;
		} else {
			return pBestFitness;
		}
	}

	private void calculateGBest(Particle particle) {
		Double[] pBest = particle.getPBest();

		Double pBestFitness = this.problem.getFitness(pBest);
		Double gBestFitness = this.problem.getFitness(this.gBest);

		if (this.problem.compareFitness(gBestFitness, pBestFitness)) {
			this.gBest = pBest;
		}
	}

	private Double[] getInitialPosition() {
		Double[] position = new Double[this.dimensions];
		Random random = new Random(System.nanoTime());

		for (int i = 0; i < this.dimensions; i++) {
			double value = random.nextDouble();

			position[i] = (this.problem.getUpperLimit(i) - this.problem
					.getLowerLimit(i))
					* value + this.problem.getLowerLimit(i);

			position[i] = (position[i] <= this.problem.getUpperLimit(i)) ? position[i]
					: this.problem.getUpperLimit(i);
			position[i] = (position[i] >= this.problem.getLowerLimit(i)) ? position[i]
					: this.problem.getLowerLimit(i);
		}

		return position;
	}

	private Double[] getZero() {
		Double[] posicao = new Double[this.dimensions];

		for (int i = 0; i < this.dimensions; i++) {
			posicao[i] = 0D;
		}

		return posicao;
	}
}