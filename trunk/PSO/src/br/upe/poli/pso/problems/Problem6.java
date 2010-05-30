package br.upe.dsc.pso.problems;

public class Problem6 implements IProblem {
    
	public String getName() {
		return "Problem 6";
	}
	
    public int getDimensionsNumber() {
            return 3;
    }
    
    public double getLowerLimit(int dimension) {
            if (dimension == 2) {
                    return MINIMUM_DIMENSION_VALUE;
            }
            return -50.0;
    }
    
    public double getUpperLimit(int dimension) {
            return 50.0;
    }
    
    public boolean compareFitness(double pBestFitness, double currentPositionFitness) {
            return currentPositionFitness > pBestFitness;
    }
    
    public double getFitness(double... dimension) {
            return 0.7 + dimension[0] * dimension[0] + 2 * dimension[1] * dimension[1] - 0.3 * Math.cos(3 * Math.PI * dimension[0]) - 0.4 * Math.cos(4 * Math.PI * dimension[1]);
    }
}
