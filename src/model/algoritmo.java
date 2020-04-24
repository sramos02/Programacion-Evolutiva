package model;

import java.util.List;

import poblacion.individuo;
import poblacion.poblacion;

public class algoritmo {
	
	private adaptacion adaptador;
	private int tam;
	
	public algoritmo() {
		adaptador=new adaptarMax();
	}

	public algoritmo(algoritmo viejo) {
		adaptador=new adaptarMax(viejo.getAdaptador());
	}
	
	private adaptacion getAdaptador() {
		return adaptador;
	}
	
	public int calcularFuncion(List<element> fenotipos) {
		int resultado=0;
		
		return resultado;
	}
	
	public void desadaptar(poblacion poblacion) {
		adaptador.deshacer(poblacion);
	}

	public void adaptar(poblacion poblacion) {
		adaptador.adaptar(poblacion);
	}
	
	public boolean best(double fitness, double fitness2) {
		if(adaptador.getAdaptado()) {
			return fitness < fitness2;
		}
		else {
			return fitness > fitness2;
		}
	}

	public boolean worst(double fitness, double fitness2) {
		if(adaptador.getAdaptado()) {
			return fitness > fitness2;
		}
		else {
			return fitness < fitness2;
		}
	}
	
	public void addElite(List<individuo> objetivo, List<individuo> fuente, double tamElite) {
		for(int i=0; i < tamElite; i++) {
			objetivo.add(new individuo(fuente.get(i)));
		}
	}

}
