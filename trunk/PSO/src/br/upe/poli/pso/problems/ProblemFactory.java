package br.upe.poli.pso.problems;

import java.util.HashMap;
import java.util.Map;

import br.upe.poli.base.Problem;

/**
 * @author Danilo
 *
 */
public class ProblemFactory {
	private static final Map<String, Problem> map = new HashMap<String, Problem>();
	private static final ProblemFactory instance = new ProblemFactory();

	private ProblemFactory() {
		inicializarMap();
	}

	private void inicializarMap() {
		map.put("Ackley", new Ackley());
		map.put("AxisParallelHyperEllipsoid", new AxisParallelHyperEllipsoid());
		map.put("Branins", new Branins());
		map.put("Circles", new Circles());
		map.put("DeJongFifth", new DeJongFifth());
		map.put("DeJongs", new DeJongs());
		map.put("DropWave", new DropWave());
		map.put("Easom", new Easom());
		map.put("EqualPeaksA", new EqualPeaksA());
		map.put("EqualPeaksB", new EqualPeaksB());
		map.put("GoldsteinPrice", new GoldsteinPrice());
		map.put("Griewangk", new Griewangk());
		map.put("Himmelblau", new Himmelblau());
		map.put("Langermann", new Langermann());
		map.put("Michalewicz", new Michalewicz());
		map.put("MovedAxisParallelHyperEllipsoid", new MovedAxisParallelHyperEllipsoid());
		map.put("Peaks", new Peaks());
		map.put("Plateaus", new Plateaus());
		map.put("Quartic", new Quartic());
		map.put("RandomPeaks", new RandomPeaks());
		map.put("Rastrigin", new Rastrigin());
		map.put("Rosenbrock", new Rosenbrock());
		map.put("RotatedHyperEllipsoid", new RotatedHyperEllipsoid());
		map.put("Schwefel", new Schwefel());
		map.put("Shubert", new Shubert());
		map.put("SixHumpCamelBack", new SixHumpCamelBack());
		map.put("Staircase", new Staircase());
		map.put("Step", new Step());
		map.put("SumOfDifferentPower", new SumOfDifferentPower());
	}

	public Problem getProblem(String name) {
		return map.get(name);
	}

	/**
	 * Método acessor para obter o valor de instance
	 * 
	 * @return O valor de instance
	 */
	public static ProblemFactory getInstance() {
		return instance;
	}

}
