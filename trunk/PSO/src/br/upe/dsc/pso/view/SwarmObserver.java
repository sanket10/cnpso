package br.upe.dsc.pso.view;

import br.upe.dsc.pso.algorithm.Particle;
import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.util.FileUtil;

public class SwarmObserver {
	private double[] xAxis;
	private double[] yAxis;
	private double[] zAxis;
	private int swarmSize;
	private IProblem problem;
	private FileUtil fileUtil;
	private int iteration;
	
	public SwarmObserver(int swarmSize, IProblem problem, FileUtil fileUtil) {
		this.problem = problem;
		this.swarmSize = swarmSize;
		xAxis = new double[swarmSize];
		yAxis = new double[swarmSize];
		zAxis = new double[swarmSize];
		iteration = 1;
		
		this.fileUtil = fileUtil;
	}

	public void update(Particle[] swarm) {
		double[] particleCurrentPosition;
		
		fileUtil.printIterationHeader(iteration++);
		
		for (int i = 0; i < swarmSize; i++) {
			particleCurrentPosition = swarm[i].getCurrentPosition();
			xAxis[i] = particleCurrentPosition[0];
			yAxis[i] = particleCurrentPosition[1];
			zAxis[i] = problem.getFitness(particleCurrentPosition);
			
			fileUtil.printPosition(xAxis[i], yAxis[i], zAxis[i]);
		}
	}
	
	public void reset() {
		iteration = 1;
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

	public void setXAxis(double[] xAxis) {
		this.xAxis = xAxis;
	}

	public void setYAxis(double[] yAxis) {
		this.yAxis = yAxis;
	}

	public IProblem getProblem() {
		return problem;
	}

	public FileUtil getFileUtil() {
		return fileUtil;
	}
}