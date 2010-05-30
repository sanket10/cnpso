package br.upe.poli.base;

import java.text.NumberFormat;


/**
 * @author Danilo
 *
 */
public class Solution implements Comparable<Solution> {
	protected double[] variables;

	protected double fitness;

	protected int cardinality;

	@Override
	public int compareTo(Solution ant) {
		return ant.fitness < this.fitness ? -1 : 1;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Solution = {");
		for (double var : variables){
			sb.append(NumberFormat.getInstance().format(var)).append(", ");
		}
		sb.append(" fitness = ").append(fitness).append("}");
		return sb.toString();
	}

	/**
	 * Método acessor para obter o valor de variables
	 *
	 * @return O valor de variables
	 */
	public double[] getVariables() {
		return variables;
	}

	/**
	 * Método acessor para modificar o valor de variables
	 *
	 * @param variables O novo valor de variables
	 */
	public void setVariables(double[] variables) {
		for (int i = 0; i < variables.length; i++){
			this.variables[i] = variables[i];	
		}
	}

	/**
	 * Método acessor para obter o valor de fitness
	 *
	 * @return O valor de fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * Método acessor para modificar o valor de fitness
	 *
	 * @param fitness O novo valor de fitness
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * Método acessor para obter o valor de cardinality
	 *
	 * @return O valor de cardinality
	 */
	public int getCardinality() {
		return cardinality;
	}

	/**
	 * Método acessor para modificar o valor de cardinality
	 *
	 * @param cardinality O novo valor de cardinality
	 */
	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}
}
