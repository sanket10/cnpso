package br.upe.dsc.pso.problemas;


public class Problema1 implements IProblema {
	@Override
	public double getFitness(Double... dimensao) {
		double result = 0;
		for (int i = 0; i < dimensao.length - 1; i++){
			result += dimensao[i] * dimensao[i] * (i + 1);
		}
		return result;
	}
	
	@Override
	public int getNumeroDimensoes() {
		return 4;
	}
	
	public double getLimiteInferior(int dimensao) {
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
