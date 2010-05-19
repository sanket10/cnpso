package br.upe.dsc.pso.algorithm;

import java.util.Random;

import br.upe.dsc.pso.problems.IProblem;

public class Particle {
	private int dimensions;
	private double[] currentPosition;
	private double[] pBest;
	private double[] velocity;
	private double currentFitness;

	/**
	 * Creates a new particle.
	 * 
	 * @param dimensions Number of dimensions in the search space.
	 */
	public Particle(int dimensions) {
		this.dimensions = dimensions;
		currentPosition = new double[dimensions];
		pBest = new double[dimensions];
		velocity = new double[dimensions];
	}

	/**
	 * Returns the current position of the particle.
	 * 
	 * @return The current position of the particle.
	 */
	public double[] getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Sets the current positions of the particle.
	 * 
	 * @param currentPosition The current positions of the particle.
	 */
	public void setCurrentPosition(double[] currentPosition) {
		this.currentPosition = currentPosition;
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
	 * Sets the best position found by the particle.
	 * 
	 * @param bestPosition The best position found by the particle.
	 */
	public void setPBest(double[] bestPosition) {
		this.pBest = bestPosition;
	}

	/**
	 * Return the current velocity of the particle.
	 * 
	 * @return The current velocity of the particle.
	 */
	public double[] getVelocity() {
		return velocity;
	}

	/**
	 * Sets the current velocity of the particle.
	 * 
	 * @param velocity The current velocity of the particle.
	 */
	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}

	public void updatePBest(IProblem problem) {
		double currentParticleFitness = problem.getFitness(currentPosition);
		double pBestFitness = problem.getFitness(pBest);

		if (problem.compareFitness(pBestFitness, currentParticleFitness)) {
			pBest = currentPosition.clone();
		}
	}
	
	/**
	 * Updates the current velocity of the particle.
	 * 
	 * @param inertialWeight The inertial weight 
	 * @param bestParticleNeighborhood The best particle in the neighborhood
	 * @param C1 The cognitive component
	 * @param C2 The social component
	 */
	public void updateVelocity(double inertialWeight,
			double[] bestParticleNeighborhood, double C1, double C2) {
		Random random = new Random();
		double R1 = random.nextDouble();
		double R2 = random.nextDouble();

		for (int i = 0; i < dimensions; i++) {
			velocity[i] = inertialWeight * velocity[i] + C1 * R1
					* (pBest[i] - currentPosition[i]) + C2 * R2
					* (bestParticleNeighborhood[i] - currentPosition[i]);
		}
	}

	/**
	 * Updates the current position of the particle.
	 */
	public void updateCurrentPosition(IProblem problem) {
		for (int i = 0; i < dimensions; i++) {
			currentPosition[i] = currentPosition[i] + velocity[i];
			
			currentPosition[i] = (currentPosition[i] <= problem.getUpperLimit(i)) ? currentPosition[i]
					: problem.getUpperLimit(i);
			currentPosition[i] = (currentPosition[i] >= problem.getLowerLimit(i)) ? currentPosition[i]
					: problem.getLowerLimit(i);
		}
		
		currentFitness = problem.getFitness(currentPosition);
	}

	public double getCurrentfitness() {
		return currentFitness;
	}
}