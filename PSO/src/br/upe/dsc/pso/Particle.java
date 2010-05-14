package br.upe.dsc.pso;

import java.util.Random;

import br.upe.dsc.pso.problemas.IProblem;

public class Particle {
	private int dimensoes;
	private Double[] posicaoAtual;
	private Double[] pBest;
	private Double[] velocidade;

	/**
	 * Cria uma nova partícula.
	 * 
	 * @param dimensoes
	 *            Número de dimensões do espaço de busca.
	 */
	public Particle(int dimensoes) {
		this.dimensoes = dimensoes;
		posicaoAtual = new Double[dimensoes];
		pBest = new Double[dimensoes];
		velocidade = new Double[dimensoes];
	}

	/**
	 * Retorna a posição atual da partícula.
	 * 
	 * @return A posição atual da partícula.
	 */
	public Double[] getPosicaoAtual() {
		return posicaoAtual;
	}

	/**
	 * Atribui a posição atual da partícula.
	 * 
	 * @param currentPosition
	 *            a posição atual da partícula.
	 */
	public void setPosicaoAtual(Double[] posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	/**
	 * Retorna a melhor posição já encontrada pela partícula.
	 * 
	 * @return A melhor posição já encontrada pela partícula.
	 */
	public Double[] getPBest() {
		return pBest;
	}

	/**
	 * Atribui a melhor posição já encontrada pela partícula.
	 * 
	 * @param melhorPosicao
	 *            a melhor posição já encontrada pela partícula.
	 */
	public void setPBest(Double[] melhorPosicao) {
		this.pBest = melhorPosicao;
	}

	/**
	 * Retorna a velocidade atual da partícula. Dimension
	 * 
	 * @return A velocidade atual da partícula.
	 */
	public Double[] getVelocidade() {
		return velocidade;
	}

	/**
	 * Atribui a velocidade atual da partícula.
	 * 
	 * @param velocidade
	 *            a velocidade atual da partícula.
	 */
	public void setVelocidade(Double[] velocidade) {
		this.velocidade = velocidade;
	}

	/**
	 * Atualiza a velocidade atual da partícula.
	 * 
	 * @param melhorParticulaVizinhanca
	 *            A partícula da vizinhança desta partícula que possui a melhor
	 *            posição.
	 */
	public void atualizarVelocidade(double inercialWeight,
			Double[] posicaoMelhorParticulaVizinhanca, Double C1, Double C2) {
		Random random = new Random();
		Double R1 = random.nextDouble();
		Double R2 = random.nextDouble();

		for (int i = 0; i < dimensoes; i++) {
			velocidade[i] = inercialWeight * velocidade[i] + C1 * R1
					* (pBest[i] - posicaoAtual[i]) + C2 * R2
					* (posicaoMelhorParticulaVizinhanca[i] - posicaoAtual[i]);
		}
	}

	/**
	 * Atualiza a posição atual da partícula.
	 */
	public void atualizarPosicaoAtual(IProblem problema) {
		for (int i = 0; i < dimensoes; i++) {
			posicaoAtual[i] = posicaoAtual[i] + velocidade[i];

			posicaoAtual[i] = (posicaoAtual[i] <= problema.getLimiteSuperior(i)) ? posicaoAtual[i]
					: problema.getLimiteSuperior(i);
			posicaoAtual[i] = (posicaoAtual[i] >= problema.getLimiteInferior(i)) ? posicaoAtual[i]
					: problema.getLimiteInferior(i);
		}
	}
}