package br.upe.dsc.pso.problems;

public interface IProblem {
	static final double MINIMUM_DIMENSION_VALUE = 0.01;
	
	int getDimensionsNumber();
	
	double getLowerLimit(int dimension);
	
	double getUpperLimit(int dimension);
	
	boolean compareFitness(Double pBestFitness, Double currentPositionFitness);
	
	double getFitness(Double... dimension);
}