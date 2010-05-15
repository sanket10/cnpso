package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problemas.IProblem;

public class PSO {
	private final double INITIAL_WEIGHT = 0.9;
	private final double FINAL_WEIGHT = 0.4;
	
	private int dimensions;
	private int swarmSize;
	private Particle swarm[];
	private Double[] gBest;
	private Double C1;
	private Double C2;
	private IProblem problem;
	
	// Current inertia factor
	private double inertiaWeight;
	private double iteration;
	private double maxIterations;
	private double standardDeviation;
	private double[] allFitness;

	public PSO(int swarmSize, int maxIterations, double standardDeviation,
			IProblem problem, Double C1, Double C2) {

		this.dimensions = problem.getDimensionsNumber();
		this.swarmSize = swarmSize;
		this.swarm = new Particle[swarmSize];
		this.allFitness = new double[swarmSize];
		this.gBest = getZero();
		this.problem = problem;
		this.C1 = C1;
		this.C2 = C2;
		this.inertiaWeight = INITIAL_WEIGHT;
		this.maxIterations = maxIterations;
		this.standardDeviation = standardDeviation;
	}

	public void run() {
		init();
		double standardDeviation;
		for (int i = 0; i < this.maxIterations; i++) {
			iteration = i;
			iterar();

			standardDeviation = Statistics.getStandardDeviation(allFitness);
			System.out.println("WEIGTH: " + inertiaWeight);
			if (standardDeviation < this.standardDeviation)
				break;
		}

		System.out.println("Best position: " + problem.getFitness(gBest));
	}

	private void init() {
		for (int i = 0; i < swarmSize; i++) {
			Particle particle = new Particle(dimensions);
			particle.setCurrentPosition(getPosicaoInicial());
			particle.setPBest(particle.getCurrentPosition());
			particle.setVelocity(getZero());
			swarm[i] = particle;
		}
	}

	private void iterar() {
		for (int i = 0; i < this.swarmSize; i++) {
			Particle particula = this.swarm[i];

			// store the better particle's position.
			this.allFitness[i] = calcularPBest(particula);
			calcularGBest(particula);
		}

		for (int i = 0; i < this.swarmSize; i++) {
			Particle particula = this.swarm[i];

			updateParticleVelocity(particula, i);
			particula.updateCurrentPosition(this.problem);
		}
	}

	private void updateParticleVelocity(Particle particulaAtual, int indice) {
		Particle melhorParticulaVizinhanca;

		melhorParticulaVizinhanca = this.swarm[melhorPosicaoVizinhanca(indice)];
		particulaAtual.updateVelocity(this.inertiaWeight,
				melhorParticulaVizinhanca.getPBest(), this.C1, this.C2);
		
		double percentcomplete = (this.iteration / this.maxIterations);
		this.inertiaWeight -= this.inertiaWeight*percentcomplete;
		
		if(inertiaWeight < FINAL_WEIGHT) 
			inertiaWeight = FINAL_WEIGHT;
	}

	private int melhorPosicaoVizinhanca(int indice) {
		int indiceMelhorParticula = indice;
		int indiceVizinhoEsquerda = (indice > 0) ? indice - 1
				: this.swarmSize - 1;
		int indiceVizinhoDireita = (indice < this.swarmSize - 1) ? indice + 1
				: 0;
		double melhor = 0.0;

		Double fitnessPBestParticulaAtual = this.problem
				.getFitness(this.swarm[indice].getPBest());
		Double fitnessPBestParticulaVizinhoEsquerda = this.problem
				.getFitness(this.swarm[indiceVizinhoEsquerda].getPBest());
		Double fitnessPBestParticulaVizinhoDireita = this.problem
				.getFitness(this.swarm[indiceVizinhoDireita].getPBest());

		melhor = fitnessPBestParticulaAtual;

		if (this.problem.compareFitness(melhor,
				fitnessPBestParticulaVizinhoEsquerda)) {
			indiceMelhorParticula = indiceVizinhoEsquerda;
			melhor = fitnessPBestParticulaVizinhoEsquerda;
		}

		if (this.problem.compareFitness(melhor,
				fitnessPBestParticulaVizinhoDireita)) {
			indiceMelhorParticula = indiceVizinhoDireita;
		}

		return indiceMelhorParticula;
	}

	private double calcularPBest(Particle particula) {
		Double[] posicaoAtual = particula.getCurrentPosition();
		Double[] pBest = particula.getPBest();

		Double fitnessPosicaoAtual = this.problem.getFitness(posicaoAtual);
		Double fitnessPBest = this.problem.getFitness(pBest);

		if (this.problem.compareFitness(fitnessPBest, fitnessPosicaoAtual)) {
			particula.setPBest(posicaoAtual);
			return fitnessPosicaoAtual;
		} else {
			return fitnessPBest;
		}
	}

	private void calcularGBest(Particle particula) {
		Double[] pBest = particula.getPBest();

		Double fitnessPBest = this.problem.getFitness(pBest);
		Double fitnessGBest = this.problem.getFitness(this.gBest);

		if (this.problem.compareFitness(fitnessGBest, fitnessPBest)) {
			this.gBest = pBest;
		}
	}

	private Double[] getPosicaoInicial() {
		Double[] posicao = new Double[this.dimensions];
		Random random = new Random(System.nanoTime());

		for (int i = 0; i < this.dimensions; i++) {
			double value = random.nextDouble();

			posicao[i] = (this.problem.getUpperLimit(i) - this.problem
					.getLowerLimit(i))
					* value + this.problem.getLowerLimit(i);

			posicao[i] = (posicao[i] <= this.problem.getUpperLimit(i)) ? posicao[i]
					: this.problem.getUpperLimit(i);
			posicao[i] = (posicao[i] >= this.problem.getLowerLimit(i)) ? posicao[i]
					: this.problem.getLowerLimit(i);
		}

		return posicao;
	}

	private Double[] getZero() {
		Double[] posicao = new Double[this.dimensions];

		for (int i = 0; i < this.dimensions; i++) {
			posicao[i] = 0D;
		}

		return posicao;
	}
}