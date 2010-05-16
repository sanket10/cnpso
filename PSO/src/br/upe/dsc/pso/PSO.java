package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problems.IProblem;

public abstract class PSO {

	private final double INITIAL_WEIGHT = 0.9;
	private final double FINAL_WEIGHT = 0.4;
	
	private int dimensions;
	private Double C1;
	private Double C2;
	protected int swarmSize;
	protected Particle swarm[];
	protected Double[] gBest;
	protected IProblem problem;
	
	// Current inertia factor
	protected double inertialWeight;
	protected double iteration;
	protected double maxIterations;
	protected double standardDeviation;
	protected double[] allFitness;

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
		this.inertialWeight = INITIAL_WEIGHT;
		this.maxIterations = maxIterations;
		this.standardDeviation = standardDeviation;
	}
	
	/**
	 * Responsible for executing the algorithm
	 * and show the result.
	 */
	public void run() {
		init();
		double standardDeviation;
		for (int i = 0; i < this.maxIterations; i++) {
			iteration = i;
			iterate();

			standardDeviation = Statistics.getStandardDeviation(allFitness);
			System.out.println("WEIGTH: " + inertialWeight);
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

			particle.updatePBest(problem);
			
			// stores the better particle's position.
			this.allFitness[i] = problem.getFitness(particle.getPBest());
			calculateGBest(particle);
		}

		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];

			updateParticleVelocity(particle, i);
			particle.updateCurrentPosition(problem);
		}
	}
	
	protected void updateParticleVelocity(Particle currentParticle, int index) {
		Particle bestParticleNeighborhood;

		bestParticleNeighborhood = getBestParticleNeighborhood(index);
		currentParticle.updateVelocity(inertialWeight,
				bestParticleNeighborhood.getPBest(), C1, C2);
		
		double percentComplete = (iteration / maxIterations);
		inertialWeight -= inertialWeight*percentComplete;
		
		if(inertialWeight < FINAL_WEIGHT) 
			inertialWeight = FINAL_WEIGHT;
	}
	
	protected void calculateGBest(Particle particle) {
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
	
	private Double[] getInitialVelocity() {
		Double[] velocity = new Double[this.dimensions];
		Random random = new Random(System.nanoTime());
		
		for (int i = 0; i < this.dimensions; i++) {
			
			// The initial velocity should be a value between zero and one
			velocity[i] = 1 / random.nextDouble();
		}
		
		return velocity;
	}
	
	private Double[] getZero() {
		Double[] posicao = new Double[this.dimensions];

		for (int i = 0; i < this.dimensions; i++) {
			posicao[i] = 0D;
		}

		return posicao;
	}
	
	protected abstract Particle getBestParticleNeighborhood(int index);
}