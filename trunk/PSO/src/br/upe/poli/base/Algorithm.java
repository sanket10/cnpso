/**
 * 
 */
package br.upe.poli.base;


/**
 * @author Danilo
 *
 */
public abstract class Algorithm {
	protected Problem problem;
	
	protected String name;

	public abstract Solution execute();
	
	/**
	 * Método acessor para obter o valor de problem
	 *
	 * @return O valor de problem
	 */
	public Problem getProblem() {
		return problem;
	}

	/**
	 * Método acessor para modificar o valor de problem
	 *
	 * @param problem O novo valor de problem
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
