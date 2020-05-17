package cruces;

import model.arbol;
import poblacion.individuo;
import poblacion.poblacion;

public class Intercambio extends algoritmoCruce{
	private double prob_terminal = 0.1;
	private double prob_func = 0.9;
	@Override
	public poblacion cruzar(poblacion seleccionados, double prob) {
		ini(prob, seleccionados);
		seleccionaReproductores();
		creaDescendientes();
		return getDescendientes();
	}

	private void creaDescendientes() {
		for(int i=0; i < getReproductoresSize(); i+=2) {
			individuo padre1=getReproductorAt(i);
			individuo padre2=getReproductorAt(i+1);
			individuo hijo1=new individuo(padre1);
			individuo hijo2=new individuo(padre2);
			//Elegir u nodo funcion 0,9 o terminal 0.1
			//Intercambiar dichos nodos
			arbol.intercambiarNodos(prob_func, prob_terminal, 
				hijo1.getCromosoma().getGenotipo(), hijo2.getCromosoma().getGenotipo());
			hijo1.calcularFenotipo();
			hijo2.calcularFenotipo();
			hijo1.calcularFitness();
			hijo2.calcularFitness();
			setDescendienteAt(i, hijo1);
			setDescendienteAt(i+1, hijo2);
		}
	}

}
