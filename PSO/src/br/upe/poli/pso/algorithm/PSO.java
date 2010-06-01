package br.upe.poli.pso.algorithm;

import br.upe.poli.base.Algorithm;
import br.upe.poli.base.Problem;
import br.upe.poli.base.Solution;
import br.upe.poli.base.SwarmObserver;
import br.upe.poli.experiments.util.Statistics;
import br.upe.poli.pso.view.SwarmObserverPSO;

public abstract class PSO extends Algorithm {

	private final double INITIAL_WEIGHT = 0.9;
	private final double FINAL_WEIGHT = 0.4;
	
	private double C1;
	private double C2;
	private SwarmObserver swarmObserver;
	private String psoType;
	protected int swarmSize;
	protected Particle swarm[];
	protected double[] gBest;
	protected double gBestFitness;
	protected Particle gBestParticle;
	
	// Current inertia factor
	protected double inertialWeight;
	protected int iteration;
	protected int maxIterations;
	protected double standardDeviation;
	protected double[] allFitness;

	public PSO(String psoType, int swarmSize, int maxIterations, double standardDeviation,
			Problem problem, double C1, double C2, SwarmObserver swarmObserver) {
		this.psoType = psoType;
		this.swarmSize = swarmSize;
		this.problem = problem;
		this.C1 = C1;
		this.C2 = C2;
		this.maxIterations = maxIterations;
		this.standardDeviation = standardDeviation;
		this.swarmObserver = swarmObserver;
	}
	
	/**
	 * Responsible for executing the algorithm
	 * and show the result.
	 */
	public Solution execute() {
		init();
		double standardDeviation;
		for (int i = 0; i < this.maxIterations; i++) {
			iteration = i;
			iterate();
			
			standardDeviation = Statistics.getStandardDeviation(allFitness);
			if (standardDeviation < this.standardDeviation) {
				break;
			}
			//if (iteration == 30) break;
		}
		// Calculating the gbest particle
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];
			
			particle.updatePBest();
			calculateGBest(particle);
		}
		return gBestParticle;
	}
	
	private void init() {
		// Init basic parameters
		this.inertialWeight = INITIAL_WEIGHT;
		this.swarm = new Particle[swarmSize];
		this.allFitness = new double[swarmSize];
		
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = new Particle(problem);
			swarm[i] = particle;
		}
		
		// Define the gBest particle of the first iteration
		gBestParticle = swarm[0];
		gBest = gBestParticle.getPBest().clone();
		gBestFitness = gBestParticle.getFitness();
		for (Particle particle : swarm) {
			calculateGBest(particle);
		}
	}
	
	private void iterate() {
		
		// Calculating the pbest and gbest positions
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];
			
			particle.updatePBest();
			calculateGBest(particle);
			
			// stores the better particle's position.
			allFitness[i] = particle.getFitness();
		}
		
		// Updating the velocity and position for all particles
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = swarm[i];
			moveParticle(particle, i);
		}
		
		// Updating the inertial weight with linear decaiment
		inertialWeight = (inertialWeight - FINAL_WEIGHT) * ((maxIterations - iteration) / (double) maxIterations) + FINAL_WEIGHT;
		
		((SwarmObserverPSO) swarmObserver).update(swarm);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
	}
	
	protected void moveParticle(Particle currentParticle, int index) {
		Particle bestParticleNeighborhood = getBestParticleNeighborhood(index);
		currentParticle.move(inertialWeight, bestParticleNeighborhood.getPBest(), C1, C2);
	}
	
	protected void calculateGBest(Particle particle) {
		if (particle.getPBestFitness() < gBestFitness) {
			gBestParticle = particle;
			gBest = gBestParticle.getPBest().clone();
			gBestFitness = gBestParticle.getPBestFitness();
		}
	}
	
	public SwarmObserver getSwarmObserver() {
		return swarmObserver;
	}
	
	@Override
	public String getName() {
		return psoType;
	}
	
	protected abstract Particle getBestParticleNeighborhood(int index);
}