package br.upe.dsc.pso.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.upe.dsc.pso.algorithm.Particle;
import br.upe.dsc.pso.problems.IProblem;

public class SwarmObserver {
	private double[] xAxis;
	private double[] yAxis;
	private double[] zAxis;
	private int swarmSize;
	private IProblem problem;
	private FileWriter writer;
	
	public SwarmObserver(int swarmSize, IProblem problem) {
		this.problem = problem;
		this.swarmSize = swarmSize;
		xAxis = new double[swarmSize];
		yAxis = new double[swarmSize];
		zAxis = new double[swarmSize];
		try {
			writer = new FileWriter(new File("output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(Particle[] swarm) {
		double[] particleCurrentPosition;
		
		for (int i = 0; i < swarmSize; i++) {
			particleCurrentPosition = swarm[i].getCurrentPosition();
			xAxis[i] = particleCurrentPosition[0];
			yAxis[i] = particleCurrentPosition[1];
			zAxis[i] = problem.getFitness(particleCurrentPosition);
			
			try {
				writer.write(xAxis[i] + " " + yAxis[i] + " " + zAxis[i] + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void end() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double[] getXAxis() {
		return xAxis;
	}

	public double[] getYAxis() {
		return yAxis;
	}

	public double[] getZAxis() {
		return zAxis;
	}
}
