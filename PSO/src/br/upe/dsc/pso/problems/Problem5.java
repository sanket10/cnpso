package br.upe.dsc.pso.problems;

public class Problem5 implements IProblem {
    
	public String getName() {
		return "Problem 5";
	}
	
    public int getDimensionsNumber() {
            return 3;
    }
    
    public double getLowerLimit(int dimension) {
            if (dimension == 0) {
                    return -3.0;
            }
            else if (dimension == 2) {
                    return MINIMUM_DIMENSION_VALUE;
            }
            return 4.1;
    }
    
    public double getUpperLimit(int dimension) {
            if (dimension == 0) {
                    return 12.1;
            }
            return 5.8;
    }
    
    public boolean compareFitness(double pBestFitness, double currentPositionFitness) {
            return currentPositionFitness > pBestFitness;
    }
    
    public double getFitness(double... dimension) {
            return 21.5 + dimension[0] * Math.sin(4 * Math.PI * dimension[0]) + dimension[1] * Math.sin(20 * Math.PI * dimension[1]);
    }
}
