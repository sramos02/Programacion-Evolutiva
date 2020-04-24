package poblacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import genetica.genes;
import init.initMethod;
import model.algoritmo;

public class individuo {
	
	private genes cromosoma;
	private int fitness;
	private algoritmo f;
	private initMethod algInit;
	
	public individuo(algoritmo f, initMethod algInit) {
		this.f = f;
		this.algInit=algInit;
		cromosoma = new genes(algInit);
		calcularFitness(); 
	}
	
	public individuo(individuo ind) {
		this.f = ind.getAlgoritmo();
		this.algInit=ind.getAlgInit();
		cromosoma = new genes(ind.getCromosoma());
		fitness = ind.getFitness();
	}

	private initMethod getAlgInit() {
		return algInit;
	}
	
	private int random(int max) {
		Random r=new Random();
		int valor;
		valor=Math.abs(r.nextInt() % max);
		return valor;
	}

	/**Transforma un n�mero en base 2 a un n�mero en base 10*/
	public double bin2dec(List<Boolean> binario) {
		int res=0;
		for(int i=binario.size()-1; i >=0 ; i--) {
			if(binario.get(i)) {
				res+=Math.pow(2, i);
			}
		}
		return res;
	}
	
	public double log2(double x) {
		return Math.log(x) / Math.log(2);
	}

	public int getFitness() {
		return fitness;
	}
	
	
	public void calcularFitness() {
		this.fitness=f.calcularFuncion(cromosoma.getFenotipo());
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
	
	/*public boolean existeGen(gen gen) {
		int i=0;
		while(i < getSizeCromosoma() && 
				cromosoma.get(i).getGenotipo() != 
				gen.getGenotipo()) {
			i++;
		}
		if(i == getSizeCromosoma()) {
			return false;
		}
		return true;
	}
	
	public boolean existeGen(gen gen, int ini, int fin) {
		int i=ini;
		while(i != fin && 
				cromosoma.get(i).getGenotipo() != 
				gen.getGenotipo()) {
			i=(i+1)%getSizeCromosoma();
		}
		if(i==fin) {
			return false;
		}
		return true;
	}

	public void quitaGen(int pos) {
		cromosoma.remove(pos);
	}

	public void cromosomaVacio() {
		cromosoma = new ArrayList<gen>();
	}

	public void add(gen elem) {
		cromosoma.add(elem);
	}*/
}
