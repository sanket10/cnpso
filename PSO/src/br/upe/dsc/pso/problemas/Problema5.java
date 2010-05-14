package br.upe.dsc.pso.problemas;

public class Problema5 implements IProblem {
	
	@Override
	public double getFitness(Double... dimensao) {
		return 21.5 + dimensao[0] * Math.sin(4 * Math.PI * dimensao[0]) + dimensao[1] * Math.sin(20 * Math.PI * dimensao[1]);
	}
	
	@Override
	public int getNumeroDimensoes() {
		return 3;
	}
	
	public double getLimiteInferior(int dimensao) {
		if (dimensao == 0) {
			return -3.0;
		}
		else if (dimensao == 2) {
			return VALOR_MIN_DIMENSAO;
		}
		return 4.1;
	}
	
	public double getLimiteSuperior(int dimensao) {
		if (dimensao == 0) {
			return 12.1;
		}
		return 5.8;
	}
	
	public boolean comparaFitness(Double fitnessPBest, Double fitnessPosicaoAtual) {
		return fitnessPosicaoAtual > fitnessPBest;
	}
}
