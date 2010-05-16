package br.upe.dsc.pso.problems;

public class Problem2 implements IProblem {
    
    public int getDimensionsNumber() {
            return 3;
    }
    
    public double getLowerLimit(int dimension) {
            if (dimension == 2) {
                    return MINIMUM_DIMENSION_VALUE;
            }
            return -2.048;
    }
    
    public double getUpperLimit(int dimension) {
            return 2.048;
    }

    public boolean compareFitness(Double pBestFitness, Double currentPositionFitness) {
            return currentPositionFitness > pBestFitness;
    }
    
    public double getFitness(Double... dimension) {
            double fator1 = (dimension[1] - dimension[0] * dimension[0]);
            return 100 * fator1 * fator1 + (1 - dimension[0]) * (1 - dimension[0]);
    }
}
