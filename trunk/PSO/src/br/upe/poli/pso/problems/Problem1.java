package br.upe.dsc.pso.problems;

public class Problem1 implements IProblem {
    
	public String getName() {
		return "Problem 1";
	}
	
    public int getDimensionsNumber() {
            return 4;
    }
    
    public double getLowerLimit(int dimension) {
            return -5.12;
    }
    
    public double getUpperLimit(int dimension) {
            return 5.12;
    }

    public boolean compareFitness(double pBestFitness, double currentPositionFitness) {
            return currentPositionFitness > pBestFitness;
    }
    
    public double getFitness(double... dimension) {
            double result = 0;
            for (int i = 0; i < dimension.length - 1; i++){
                    result += dimension[i] * dimension[i] * (i + 1);
            }
            return result;
    }
}
