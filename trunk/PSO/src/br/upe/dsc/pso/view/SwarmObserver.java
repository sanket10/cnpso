package br.upe.dsc.pso.view;

import br.upe.dsc.pso.algorithm.Particle;
import br.upe.dsc.pso.problems.IProblem;

public class SwarmObserver {
	private Double[] xAxis;
	private Double[] yAxis;
	private Double[] zAxis;
	private int swarmSize;
	private IProblem problem;
	
	public SwarmObserver(int swarmSize, IProblem problem) {
		this.problem = problem;
		this.swarmSize = swarmSize;
		xAxis = new Double[swarmSize];
		yAxis = new Double[swarmSize];
		zAxis = new Double[swarmSize];
	}

	public void update(Particle[] swarm) {
		Double[] particleCurrentPosition;
		
		for (int i = 0; i < swarmSize; i++) {
			particleCurrentPosition = swarm[i].getCurrentPosition();
			xAxis[i] = particleCurrentPosition[0];
			yAxis[i] = particleCurrentPosition[1];
			zAxis[i] = problem.getFitness(particleCurrentPosition);
		}
	}
	
	public Double[] getXAxis() {
		return xAxis;
	}

	public Double[] getYAxis() {
		return yAxis;
	}

	public Double[] getZAxis() {
		return zAxis;
	}
}
