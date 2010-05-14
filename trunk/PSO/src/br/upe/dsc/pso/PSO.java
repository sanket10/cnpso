package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problemas.IProblem;

public class PSO {
	private int dimensions;
	private int swarmSize;
	private Particle swarm[];
	private Double[] gBest;
	private Double C1;
	private Double C2;
	private IProblem problem;
	// Fator de inércia atual
	private double inercialWeight;
	private final double WINI = 0.9;
	private final double WFIM = 0.4;
	private int iteration;
	private int maxIterations;
	private double standardDeviation;

	public PSO(int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, Double C1, Double C2) {

		this.dimensions = problem.getNumeroDimensoes();
		this.swarmSize = swarmSize;
		swarm = new Particle[swarmSize];
		gBest = getZero();
		this.problem = problem;
		this.C1 = C1;
		this.C2 = C2;
		this.inercialWeight = WINI;
		this.maxIterations = maxIterations;
		this.standardDeviation = standardDeviation;
	}

	public void run() {
		init();
		double standardDeviation;
		for (int i = 0; i < this.maxIterations; i++) {
			iteration = i;
			iterar();

			standardDeviation = 0.0;

			if (standardDeviation < this.standardDeviation)
				break;
		}

		System.out.println("Melhor posição: " + problem.getFitness(gBest));
	}

	private void init() {
		for (int i = 0; i < swarmSize; i++) {
			Particle particula = new Particle(dimensions);
			particula.setPosicaoAtual(getPosicaoInicial());
			particula.setPBest(particula.getPosicaoAtual());
			particula.setVelocidade(getZero());
			swarm[i] = particula;
		}
	}

	private void iterar() {
		for (int i = 0; i < swarmSize; i++) {
			Particle particula = swarm[i];

			calcularPBest(particula);
			calcularGBest(particula);
		}

		for (int i = 0; i < swarmSize; i++) {
			Particle particula = swarm[i];

			updateParticleVelocity(particula, i);
			particula.atualizarPosicaoAtual(problem);
		}
	}

	private void updateParticleVelocity(Particle particulaAtual, int indice) {
		Particle melhorParticulaVizinhanca;

		melhorParticulaVizinhanca = swarm[melhorPosicaoVizinhanca(indice)];
		particulaAtual.atualizarVelocidade(inercialWeight,
				melhorParticulaVizinhanca.getPBest(), C1, C2);

		inercialWeight = (inercialWeight - WFIM)
				* ((maxIterations - iteration) / maxIterations) + WFIM;
	}

	private int melhorPosicaoVizinhanca(int indice) {
		int indiceMelhorParticula = indice;
		int indiceVizinhoEsquerda = (indice > 0) ? indice - 1 : swarmSize - 1;
		int indiceVizinhoDireita = (indice < swarmSize - 1) ? indice + 1 : 0;
		double melhor = 0.0;

		Double fitnessPBestParticulaAtual = problem.getFitness(swarm[indice]
				.getPBest());
		Double fitnessPBestParticulaVizinhoEsquerda = problem
				.getFitness(swarm[indiceVizinhoEsquerda].getPBest());
		Double fitnessPBestParticulaVizinhoDireita = problem
				.getFitness(swarm[indiceVizinhoDireita].getPBest());

		melhor = fitnessPBestParticulaAtual;

		if (problem
				.comparaFitness(melhor, fitnessPBestParticulaVizinhoEsquerda)) {
			indiceMelhorParticula = indiceVizinhoEsquerda;
			melhor = fitnessPBestParticulaVizinhoEsquerda;
		}

		if (problem.comparaFitness(melhor, fitnessPBestParticulaVizinhoDireita)) {
			indiceMelhorParticula = indiceVizinhoDireita;
		}

		return indiceMelhorParticula;
	}

	private void calcularPBest(Particle particula) {
		Double[] posicaoAtual = particula.getPosicaoAtual();
		Double[] pBest = particula.getPBest();

		Double fitnessPosicaoAtual = problem.getFitness(posicaoAtual);
		Double fitnessPBest = problem.getFitness(pBest);

		if (problem.comparaFitness(fitnessPBest, fitnessPosicaoAtual)) {
			particula.setPBest(posicaoAtual);
		}
	}

	private void calcularGBest(Particle particula) {
		Double[] pBest = particula.getPBest();

		Double fitnessPBest = problem.getFitness(pBest);
		Double fitnessGBest = problem.getFitness(gBest);

		if (problem.comparaFitness(fitnessGBest, fitnessPBest)) {
			gBest = pBest;
		}
	}

	private Double[] getPosicaoInicial() {
		Double[] posicao = new Double[dimensions];
		Random random = new Random(System.nanoTime());

		for (int i = 0; i < dimensions; i++) {
			double value = random.nextDouble();

			posicao[i] = (problem.getLimiteSuperior(i) - problem
					.getLimiteInferior(i))
					* value + problem.getLimiteInferior(i);

			posicao[i] = (posicao[i] <= problem.getLimiteSuperior(i)) ? posicao[i]
					: problem.getLimiteSuperior(i);
			posicao[i] = (posicao[i] >= problem.getLimiteInferior(i)) ? posicao[i]
					: problem.getLimiteInferior(i);
		}

		return posicao;
	}

	private Double[] getZero() {
		Double[] posicao = new Double[dimensions];

		for (int i = 0; i < dimensions; i++) {
			posicao[i] = 0D;
		}

		return posicao;
	}
}