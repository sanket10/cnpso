package br.upe.dsc.pso.problems;

public class Problem4 implements IProblem {

	public String getName() {
		return "Problem 4";
	}
	
    public int getDimensionsNumber() {
            return 31;
    }
    
    public double getLowerLimit(int dimension) {
            if (dimension == 30) {
                    return MINIMUM_DIMENSION_VALUE;
            }
            return -1.28;
    }
    
    public double getUpperLimit(int dimension) {
            return 1.28;
    }
    
    public boolean compareFitness(double pBestFitness, double currentPositionFitness) {
            return currentPositionFitness > pBestFitness;
    }
    
    public double getFitness(double... dimension) {
            double result = 0;
            for (int i = 0; i < dimension.length - 1; i++){
                    result += dimension[i] * dimension[i] * dimension[i] * dimension[i] * (i + 1);
            }
            return result;
    }
}
