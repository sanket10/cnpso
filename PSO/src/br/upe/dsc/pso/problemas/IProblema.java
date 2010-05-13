package br.upe.dsc.pso.problemas;

public interface IProblema {
	static final double VALOR_MIN_DIMENSAO = 0.01;
	
	double getFitness(Double... dimensao);
	
	boolean comparaFitness(Double fitnessPBest, Double fitnessPosicaoAtual);
	
	int getNumeroDimensoes();
	
	double getLimiteInferior(int dimensao);
	
	double getLimiteSuperior(int dimensao);
}