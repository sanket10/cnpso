package br.upe.dsc.pso.problemas;

public interface IProblem {
	static final double MINIMUM_DIMENSION_VALUE = 0.01;
	
	double getFitness(Double... dimension);
	
	boolean compareFitness(Double pBestFitness, Double currentPositionfitness);
	
	int getDimensionsNumber();
	
	double getLowerLimit(int dimension);
	
	double getUpperLimit(int dimension);
}