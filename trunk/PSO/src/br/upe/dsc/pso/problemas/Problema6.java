package br.upe.dsc.pso.problemas;

public class Problema6 implements IProblema {
	
	public double getFitness(Double... dimensao) {
		return 0.7 + dimensao[0] * dimensao[0] + 2 * dimensao[1] * dimensao[1] - 0.3 * Math.cos(3 * Math.PI * dimensao[0]) - 0.4 * Math.cos(4 * Math.PI * dimensao[1]);
	}

	public int getNumeroDimensoes() {
		return 3;
	}
	
	public double getLimiteInferior(int dimensao) {
		if (dimensao == 2) {
			return VALOR_MIN_DIMENSAO;
		}
		return -50.0;
	}
	
	public double getLimiteSuperior(int dimensao) {
		return 50.0;
	}
	
	public boolean comparaFitness(Double fitnessPBest, Double fitnessPosicaoAtual) {
		return fitnessPosicaoAtual > fitnessPBest;
	}
}
