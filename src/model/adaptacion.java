package model;

import poblacion.individuo;
import poblacion.poblacion;

public class adaptacion {
	private final double c=1.05;
	private double limit;
	private boolean adaptado;
	
	public adaptacion(adaptacion viejo) {
		limit=viejo.getLimit();
		adaptado=viejo.getAdaptado();
	}
	public adaptacion() {}
	
	public void adaptar(poblacion p) {
		adaptado=true;
		double valor=p.getIndividuo(0).getFitness();
		
		for(int i=1; i < p.getSize(); i++) {
			if(p.getIndividuo(i).getFitness() < valor) {
				valor=p.getIndividuo(i).getFitness();
			}
		}
		valor = valor * c;
		limit = (int)valor;
		
		//Ajusta el resultado
		for(int i=0; i < p.getSize(); i++) {
			individuo ind=p.getIndividuo(i);
			ind.setFitness((int)(ind.getFitness() + limit));
		}
	}
	
	public void deshacer(poblacion p) {
		double valor;
		for(int i=0; i < p.getSize(); i++) {
			valor=(p.getIndividuo(i).getFitness() - limit);
			p.getIndividuo(i).setFitness((int)valor);
		}
		adaptado = false;
	}
	
	public double getLimit() {
		return limit;
	}

	public boolean getAdaptado() {
		return adaptado;
	}

}
