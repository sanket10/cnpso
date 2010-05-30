package br.upe.poli.pso.view;

import br.upe.poli.pso.algorithm.Particle;
import br.upe.poli.base.Problem;
import br.upe.poli.pso.util.FileUtil;
import br.upe.poli.base.SwarmObserver;

public class SwarmObserverPSO extends SwarmObserver {
	/*
	private double[] xAxis;
	private double[] yAxis;
	private double[] zAxis;
	*/
	private int swarmSize;
	private Problem problem;
	private FileUtil fileUtil;
	private int iteration;
	
	public SwarmObserverPSO(int swarmSize, Problem problem, FileUtil fileUtil) {
		this.problem = problem;
		this.swarmSize = swarmSize;
		dataX = new double[swarmSize];
		dataY = new double[swarmSize];
		dataZ = new double[swarmSize];
		iteration = 1;
		
		this.fileUtil = fileUtil;
	}

	public void update(Particle[] swarm) {
		double[] particleCurrentPosition;
		
		fileUtil.printIterationHeader(iteration++);
		
		for (int i = 0; i < swarmSize; i++) {
			particleCurrentPosition = swarm[i].getVariables();
			dataX[i] = particleCurrentPosition[0];
			dataY[i] = particleCurrentPosition[1];
			dataZ[i] = problem.getFitness(particleCurrentPosition);
			
			fileUtil.printPosition(dataX[i], dataY[i], dataZ[i]);
		}
	}
	
	public void reset() {
		iteration = 1;
	}
	/*
	public double[] getXAxis() {
		return xAxis;
	}

	public double[] getYAxis() {
		return yAxis;
	}

	public double[] getZAxis() {
		return zAxis;
	}

	public void setXAxis(double[] xAxis) {
		this.xAxis = xAxis;
	}

	public void setYAxis(double[] yAxis) {
		this.yAxis = yAxis;
	}
*/
	public Problem getProblem() {
		return problem;
	}

	public FileUtil getFileUtil() {
		return fileUtil;
	}
}