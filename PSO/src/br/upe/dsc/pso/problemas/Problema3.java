package br.upe.dsc.pso.problemas;

public class Problema3 implements IProblema {
	@Override
	public double getFitness(Double... dimensao) {
		double result = 0;
		for (int i = 0; i < dimensao.length - 1; i++){
			result += Math.floor(dimensao[i]);
		}
		return result;
	}
	
	@Override
	public int getNumeroDimensoes() {
		return 6;
	}
	
	public double getLimiteInferior(int dimensao) {
		if (dimensao == 5) {
			return VALOR_MIN_DIMENSAO;
		}
		return -5.12;
	}
	
	public double getLimiteSuperior(int dimensao) {
		return 5.12;
	}
	
	@Override
	public boolean comparaFitness(Double fitnessPBest, Double fitnessPosicaoAtual) {
		return fitnessPosicaoAtual > fitnessPBest;
	}
}
