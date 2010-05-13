package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problemas.IProblema;

public class PSO {
	private int dimensoes;
	private int tamanhoEnxame;
	private Particula enxame[];
	private Double[] gBest;
	private Double C1;
	private Double C2;
	private IProblema problema;
	
	public PSO(int tamanhoEnxame, IProblema problema, Double C1, Double C2) {
		this.dimensoes = problema.getNumeroDimensoes();
		this.tamanhoEnxame = tamanhoEnxame;
		enxame = new Particula[tamanhoEnxame];
		gBest = getZero();
		this.problema = problema;
		this.C1 = C1;
		this.C2 = C2;
	}
	
	public void rodar() {
		inicializar();
		for (int i = 0; i < 1000; i++) {
			iterar();
		}
		
		System.out.println("Melhor posição: " + problema.getFitness(gBest));
	}
	
	private void inicializar() {
		for (int i = 0; i < tamanhoEnxame; i++) {
			Particula particula = new Particula(dimensoes);			 
			particula.setPosicaoAtual(getPosicaoInicial());
			particula.setPBest(particula.getPosicaoAtual());
			particula.setVelocidade(getZero());
			enxame[i] = particula;
		}		
	}
	
	private void iterar() {
		for (int i = 0; i < tamanhoEnxame; i++) {
			Particula particula = enxame[i];
			
			calcularPBest(particula);
			calcularGBest(particula);
		}
		
		for (int i = 0; i < tamanhoEnxame; i++) {
			Particula particula = enxame[i];
			
			atualizarVelocidade(particula, i);
			particula.atualizarPosicaoAtual(problema);
		}
	}
	
	private void atualizarVelocidade(Particula particulaAtual, int indice) {
		Particula melhorParticulaVizinhanca;
		
		melhorParticulaVizinhanca = enxame[melhorPosicaoVizinhanca(indice)];
		particulaAtual.atualizarVelocidade(melhorParticulaVizinhanca.getPBest(), C1, C2);
	}
	
	private int melhorPosicaoVizinhanca(int indice) {
		int indiceMelhorParticula = indice;
		int indiceVizinhoEsquerda = (indice > 0) ? indice - 1 : tamanhoEnxame - 1;
		int indiceVizinhoDireita = (indice < tamanhoEnxame - 1) ? indice + 1 : 0;	
		double melhor = 0.0;
		
		Double fitnessPBestParticulaAtual = problema.getFitness(enxame[indice].getPBest());
		Double fitnessPBestParticulaVizinhoEsquerda = problema.getFitness(enxame[indiceVizinhoEsquerda].getPBest());
		Double fitnessPBestParticulaVizinhoDireita = problema.getFitness(enxame[indiceVizinhoDireita].getPBest());
		
		melhor = fitnessPBestParticulaAtual;
		
		if (problema.comparaFitness(melhor, fitnessPBestParticulaVizinhoEsquerda)) {
			indiceMelhorParticula = indiceVizinhoEsquerda;
			melhor = fitnessPBestParticulaVizinhoEsquerda;
		}
		
		if (problema.comparaFitness(melhor, fitnessPBestParticulaVizinhoDireita)) {
			indiceMelhorParticula = indiceVizinhoDireita;
		}
		
		return indiceMelhorParticula;
	}
	
	private void calcularPBest(Particula particula) {
		Double[] posicaoAtual = particula.getPosicaoAtual();
		Double[] pBest = particula.getPBest();
		
		Double fitnessPosicaoAtual = problema.getFitness(posicaoAtual);
		Double fitnessPBest = problema.getFitness(pBest);
		
		if (problema.comparaFitness(fitnessPBest, fitnessPosicaoAtual)) {
			particula.setPBest(posicaoAtual);
		}
	}
	
	private void calcularGBest(Particula particula) {
		Double[] pBest = particula.getPBest();
		
		Double fitnessPBest = problema.getFitness(pBest);
		Double fitnessGBest = problema.getFitness(gBest);
		
		if (problema.comparaFitness(fitnessGBest, fitnessPBest)) {
			gBest = pBest;
		}
	}
	
	private Double[] getPosicaoInicial() {
		Double[] posicao = new Double[dimensoes];
		Random random = new Random(System.nanoTime());
		
		for (int i = 0; i < dimensoes; i++) {
			double value = random.nextDouble();
			
			posicao[i] = (problema.getLimiteSuperior(i) - problema.getLimiteInferior(i)) * value + problema.getLimiteInferior(i);

			posicao[i] = (posicao[i] <= problema.getLimiteSuperior(i)) ?
					posicao[i] : problema.getLimiteSuperior(i);		
			posicao[i] = (posicao[i] >= problema.getLimiteInferior(i)) ?
					posicao[i] : problema.getLimiteInferior(i);
		}
		
		return posicao;
	}
	
	private Double[] getZero() {
		Double[] posicao = new Double[dimensoes];
		
		for (int i = 0; i < dimensoes; i++) {
			posicao[i] = 0D;
		}
		
		return posicao;
	}
}