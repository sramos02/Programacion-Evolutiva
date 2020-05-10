package poblacion;

import genetica.genes;
import init.initMethod;
import model.algoritmo;

public class individuo {
	
	private genes cromosoma;
	private int fitness;
	private algoritmo f;
	
	public individuo(algoritmo f, initMethod algInit, int numVars) {
		this.f = f;
		cromosoma = new genes(algInit, numVars);
		calcularFitness(); 
	}
	
	public individuo(individuo ind) {
		this.f = new algoritmo(ind.getAlgoritmo());
		cromosoma = new genes(ind.getCromosoma());
		fitness = ind.getFitness();
	}

	public int getFitness() {
		return fitness;
	}
	
	public void calcularFitness() {
		this.fitness=f.calcularFuncion(cromosoma.getFenotipoList());
	}
	
	public genes getCromosoma(){
		return cromosoma;
	}
	
	public algoritmo getAlgoritmo() {
		return f;
	}
	
	public void setGen(genes gen) {
		cromosoma=gen;
	}
	

	public void setFitness(int d) {
		fitness=d;
	}

	public String getExpresion() {
		return cromosoma.getFenotipo();
	}
	
}
