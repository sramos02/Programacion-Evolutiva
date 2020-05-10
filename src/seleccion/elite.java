package seleccion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import poblacion.*;

public class elite {
	
	private List<individuo> elite;
	private double tamElite;
	
	public void escogerElites(poblacion poblacion, double por) {
		elite=new ArrayList<individuo>();
		List<individuo> aux=new ArrayList<individuo>();
		//Copia los individuos 
		for(int i=0; i < poblacion.getSize(); i++) {
			aux.add(poblacion.getIndividuo(i));
		}
		//Ordena los individuos
		aux.sort(new Comparator<individuo>() {

			@Override
			public int compare(individuo i1, individuo i2) {
				return new Double(i1.getFitness()).compareTo(new Double(i2.getFitness()));
			}});
		//Determina el tamaï¿½o de la elite
		tamElite=Math.floor(poblacion.getSize()*por);
		//Aï¿½ade la elite
		poblacion.getFuncion().addElite(elite, aux, tamElite);
		
	}
	/**
	 * Añade la elite, previamente escogida, a la nueva poblaciï¿½n*/
	public void incluirElites(poblacion poblacion) {
		int last=poblacion.getSize()-1;
		for(int i=0; i < elite.size(); i++ ) {
			poblacion.setIndividuoAt(last-i, elite.get(i));
		}
		elite.clear();
	}
	
}
