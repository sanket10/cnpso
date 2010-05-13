package br.upe.dsc.pso.problemas;

public class Problema4 implements IProblema {

	@Override
	public double getFitness(Double... dimensao) {
		double result = 0;
		for (int i = 0; i < dimensao.length - 1; i++){
			result += dimensao[i] * dimensao[i] * dimensao[i] * dimensao[i] * (i + 1);
		}
		return result;
	}
	
	@Override
	public int getNumeroDimensoes() {
		return 31;
	}
	
	public double getLimiteInferior(int dimensao) {
		if (dimensao == 30) {
			return VALOR_MIN_DIMENSAO;
		}
		return -1.28;
	}
	
	public double getLimiteSuperior(int dimensao) {
		return 1.28;
	}
	
	@Override
	public boolean comparaFitness(Double fitnessPBest, Double fitnessPosicaoAtual) {
		return fitnessPosicaoAtual > fitnessPBest;
	}
}
