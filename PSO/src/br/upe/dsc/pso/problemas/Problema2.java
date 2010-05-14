package br.upe.dsc.pso.problemas;

public class Problema2 implements IProblem{
	
	@Override
	public boolean comparaFitness(Double fitnessPBest, Double fitnessPosicaoAtual) {
		return fitnessPosicaoAtual > fitnessPBest;
	}

	@Override
	public double getFitness(Double... dimensao) {
		double fator1 = (dimensao[1] - dimensao[0] * dimensao[0]);
		return 100 * fator1 * fator1 + (1 - dimensao[0]) * (1 - dimensao[0]);
	}
	
	@Override
	public int getNumeroDimensoes() {
		return 3;
	}
	
	public double getLimiteInferior(int dimensao) {
		if (dimensao == 2) {
			return VALOR_MIN_DIMENSAO;
		}
		return -2.048;
	}
	
	public double getLimiteSuperior(int dimensao) {
		return 2.048;
	}

}
