package poblacion;

import genetica.genes;
import init.initMethod;
import model.algoritmo;

public class individuo {
	
	private genes cromosoma;
	private int fitness;
	private algoritmo f;
	
	public individuo(algoritmo f, initMethod algInit) {
		this.f = f;
		cromosoma = new genes(algInit);
		calcularFitness(); 
	}
	
	public individuo(individuo ind) {
		this.f = ind.getAlgoritmo();
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
	
	public int getSizeCromosoma(){
		return cromosoma.size();
	}

	public void setFitness(int d) {
		fitness=d;
	}

	public String getExpresion() {
		return cromosoma.getFenotipo();
	}
	
}
