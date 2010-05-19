package br.upe.dsc.pso.algorithm;

import java.util.Random;

import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.view.SwarmObserver;

public abstract class PSO {

	private final double INITIAL_WEIGHT = 0.9;
	private final double FINAL_WEIGHT = 0.4;
	
	private int dimensions;
	private double C1;
	private double C2;
	private SwarmObserver swarmObserver;
	private String psoType;
	protected int swarmSize;
	protected Particle swarm[];
	protected double[] gBest;
	protected IProblem problem;
	
	// Current inertia factor
	protected double inertialWeight;
	protected int iteration;
	protected int maxIterations;
	protected double standardDeviation;
	protected double[] allFitness;

	public PSO(String psoType, int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, double C1, double C2, SwarmObserver swarmObserver) {

		this.psoType = psoType;
		this.dimensions = problem.getDimensionsNumber();
		this.swarmSize = swarmSize;
		this.swarm = new Particle[swarmSize];
		this.allFitness = new double[swarmSize];
		this.gBest = null;
		this.problem = problem;
		this.C1 = C1;
		this.C2 = C2;
		this.inertialWeight = INITIAL_WEIGHT;
		this.maxIterations = maxIterations;
		this.standardDeviation = standardDeviation;
		this.swarmObserver = swarmObserver;
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
//			System.out.println("WEIGTH: " + inertialWeight);
			if (standardDeviation < this.standardDeviation) {
				break;
			}
		}
		
		System.out.println("Best position: " + problem.getFitness(gBest));
	}

	private void init() {
		swarmObserver.getFileUtil().printFileHeader(psoType, swarmSize, maxIterations, standardDeviation, C1, C2);
		
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = new Particle(dimensions);
			particle.setCurrentPosition(getInitialPosition());
			particle.setPBest(particle.getCurrentPosition().clone());
			particle.setVelocity(getInitialVelocity());
			swarm[i] = particle;
		}
		
		// Define the gBest particle of the first iteration
		this.gBest = swarm[0].getCurrentPosition().clone();
		for (Particle particle : swarm) {
			calculateGBest(particle);
		}
	}
	
	private void iterate() {
		
		// Calculating the pbest and gbest positions
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];

			particle.updatePBest(problem);
			
			// stores the better particle's position.
			this.allFitness[i] = problem.getFitness(particle.getPBest());
			calculateGBest(particle);
		}
		
		// Updating the velocity and position for all particles
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];

			updateParticleVelocity(particle, i);
			particle.updateCurrentPosition(problem);
		}
		
		// Updating the inertial weight with linear decaiment
		inertialWeight = (inertialWeight - FINAL_WEIGHT) * ((maxIterations - iteration) / (double) maxIterations) + FINAL_WEIGHT;
		
		swarmObserver.update(swarm);
		
//		System.out.println("Current best position ["+ iteration +"/"+ maxIterations +"]: " + problem.getFitness(this.gBest));
		
		// Controls the velocity which the particles moves on the screen
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) { }
	}
	
	protected void updateParticleVelocity(Particle currentParticle, int index) {
		Particle bestParticleNeighborhood;

		bestParticleNeighborhood = getBestParticleNeighborhood(index);
		currentParticle.updateVelocity(inertialWeight,
				bestParticleNeighborhood.getPBest(), C1, C2);
	}
	
	protected void calculateGBest(Particle particle) {
		double[] pBest = particle.getPBest();

		double pBestFitness = this.problem.getFitness(pBest);
		double gBestFitness = this.problem.getFitness(this.gBest);

		if (this.problem.compareFitness(gBestFitness, pBestFitness)) {
			this.gBest = pBest.clone();
		}
	}
	
	private double[] getInitialPosition() {
		double[] position = new double[this.dimensions];
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
	
	private double[] getInitialVelocity() {
		double[] velocity = new double[this.dimensions];
		Random random = new Random(System.nanoTime());
		
		for (int i = 0; i < this.dimensions; i++) {
			
			// The initial velocity ought be a value between zero and one
			velocity[i] = random.nextDouble();
		}
		
		return velocity;
	}
	
	public SwarmObserver getSwarmObserver() {
		return swarmObserver;
	}
	
	protected abstract Particle getBestParticleNeighborhood(int index);
}