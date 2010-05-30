package br.upe.poli.pso.algorithm;

import java.util.Random;

import br.upe.poli.base.Problem;
import br.upe.poli.base.Solution;

public class Particle extends Solution {
	private double[] pBest;
	private double[] velocity;
	private double pBestFitness;
	private Problem problem;
	
	/**
	 * Creates a new particle.
	 * 
	 * @param dimensions Number of dimensions in the search space.
	 */
	public Particle(Problem problem) {
		this.problem = problem;
		cardinality = problem.getNDimensions();
		variables = new double[cardinality];
		velocity = new double[cardinality];
		
		// Defining the initial position and the initial velocity
		double[] leftBounds = problem.getLeftBounds();
		double[] rightBounds = problem.getRightBounds();
		Random random = new Random(System.nanoTime());
		for (int i = 0; i < cardinality; i++) {
			variables[i] = leftBounds[i] + ((rightBounds[i] - leftBounds[i]) * random.nextDouble());
			if (variables[i] > rightBounds[i]) variables[i] = rightBounds[i];
			if (variables[i] < leftBounds[i]) variables[i] = leftBounds[i];
			
			// The initial velocity ought be a value between zero and one
			velocity[i] = random.nextDouble();
		}
		fitness = problem.getFitness(variables);
		
		// Store the pBest equals to initial position
		pBest = variables.clone();
		pBestFitness = fitness;
	}
	
	/**
	 * Returns the best position found by the particle.
	 * 
	 * @return The best position found by the particle.
	 */
	public double[] getPBest() {
		return pBest;
	}
	
	/**
	 * Returns the best fitness found by the particle.
	 * 
	 * @return The best fitness found by the particle.
	 */
	public double getPBestFitness() {
		return pBestFitness;
	}
	
	/**
	 * Return the current velocity of the particle.
	 * 
	 * @return The current velocity of the particle.
	 */
	public double[] getVelocity() {
		return velocity;
	}
	
	public void updatePBest() {
		if (fitness < pBestFitness) {
			pBest = variables.clone();
			pBestFitness = fitness;
		}
	}
	
	/**
	 * Updates the current velocity and position of the particle 
	 * 
	 * @param inertialWeight The inertial weight 
	 * @param bestParticleNeighborhood The best particle in the neighborhood
	 * @param C1 The cognitive component
	 * @param C2 The social component
	 */
	public void move(double inertialWeight, double[] bestParticleNeighborhood, double C1, double C2) {
		double[] leftBounds = problem.getLeftBounds();
		double[] rightBounds = problem.getRightBounds();
		Random random = new Random();
		double R1 = random.nextDouble();
		double R2 = random.nextDouble();
		
		for (int i = 0; i < cardinality; i++) {
			// Updating the velocity
			velocity[i] = (inertialWeight * velocity[i])
						+ (C1 * R1 * (pBest[i] - variables[i]))
						+ (C2 * R2 * (bestParticleNeighborhood[i] - variables[i]));
			
			// Updating the position
			variables[i] = variables[i] + velocity[i];
			if (variables[i] > rightBounds[i]) variables[i] = rightBounds[i];
			if (variables[i] < leftBounds[i]) variables[i] = leftBounds[i];
		}
		// Updating the fitness
		fitness = problem.getFitness(variables);
	}
	
	public int compareTo(Particle par) {
		return par.fitness < this.fitness ? -1 : 1;
	}
	
}