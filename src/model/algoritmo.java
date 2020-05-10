package model;

import java.util.List;

import poblacion.individuo;
import poblacion.poblacion;

public class algoritmo {
	
	private adaptacion adaptador;
	private int numVariables;
	private int numSelectores;
	
	public algoritmo() {
		adaptador=new adaptarMax();
	}

	public algoritmo(algoritmo viejo) {
		adaptador=new adaptarMax(viejo.getAdaptador());
		numVariables = viejo.getNumVariables();
		numSelectores = viejo.getNumSelectores();
	}
	
	private int getNumSelectores() {
		return numSelectores;
	}

	private adaptacion getAdaptador() {
		return adaptador;
	}
	
	public void setNumVariables(int num) {
		numVariables=num;
		numSelectores = num == 6 ? 2:3; 
	}
	
	public int calcularFuncion(List<element> fenotipos) {
		int resultado=0;
		int k=0;
		int sol []=new int [numVariables];
		resultado=generar(k, sol, fenotipos);
		return resultado;
	}
	
	private int generar(int k, int[] sol, List<element> fenotipos) {
		int numSoluciones=0;
		for (int i = 0; i < 2; i++) {
			sol[k]=i;
			if(k == numVariables-1) {
				int resultadoExpresion=fenotipos.get(0).evaluarExpresion(new contador(), sol, fenotipos);
				int resultadoMux = obtenerSalida(sol);
				numSoluciones += resultadoExpresion == resultadoMux ? 1 : 0; 
			}
			else {
				numSoluciones += generar(k+1, sol, fenotipos);
			}
		}
		return numSoluciones;
	}

	private int obtenerSalida(int[] sol) {
		int seleccion=0;
		for (int i = numSelectores-1; i >= 0; i--) {
			if(sol[i]==1) {
				seleccion += Math.pow(2, numSelectores-i-1);
			}
		}
		return sol[seleccion + numSelectores];
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
		int last=fuente.size()-1;
		for(int i=0; i < tamElite; i++) {
			objetivo.add(new individuo(fuente.get(last-i)));
		}
	}

	public int getNumVariables() {
		return numVariables;
	}

}
