package br.upe.dsc.pso.problems;

public interface IProblem {
	static final double MINIMUM_DIMENSION_VALUE = 0.01;
	
	String getName();
	
	int getDimensionsNumber();
	
	double getLowerLimit(int dimension);
	
	double getUpperLimit(int dimension);
	
	boolean compareFitness(double pBestFitness, double currentPositionFitness);
	
	double getFitness(double... dimension);
}