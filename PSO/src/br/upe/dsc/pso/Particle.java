package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problems.IProblem;

public class Particle {
	private int dimensions;
	private Double[] currentPosition;
	private Double[] pBest;
	private Double[] velocity;

	/**
	 * Creates a new particle.
	 * 
	 * @param dimensions Number of dimensions in the search space.
	 */
	public Particle(int dimensions) {
		this.dimensions = dimensions;
		currentPosition = new Double[dimensions];
		pBest = new Double[dimensions];
		velocity = new Double[dimensions];
	}

	/**
	 * Returns the current position of the particle.
	 * 
	 * @return The current position of the particle.
	 */
	public Double[] getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Sets the current positions of the particle.
	 * 
	 * @param currentPosition The current positions of the particle.
	 */
	public void setCurrentPosition(Double[] currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * Returns the best position found by the particle.
	 * 
	 * @return The best position found by the particle.
	 */
	public Double[] getPBest() {
		return pBest;
	}

	/**
	 * Sets the best position found by the particle.
	 * 
	 * @param bestPosition The best position found by the particle.
	 */
	public void setPBest(Double[] bestPosition) {
		this.pBest = bestPosition;
	}

	/**
	 * Return the current velocity of the particle.
	 * 
	 * @return The current velocity of the particle.
	 */
	public Double[] getVelocity() {
		return velocity;
	}

	/**
	 * Sets the current velocity of the particle.
	 * 
	 * @param velocity The current velocity of the particle.
	 */
	public void setVelocity(Double[] velocity) {
		this.velocity = velocity;
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
			Double[] bestParticleNeighborhood, Double C1, Double C2) {
		Random random = new Random();
		Double R1 = random.nextDouble();
		Double R2 = random.nextDouble();

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
	}
}