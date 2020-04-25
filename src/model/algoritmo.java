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
	}
	
	
	
	public void setNumVariables(int num) {
		numVariables=num;
		numSelectores = num == 6 ? 2:3; //Esto no es general, se haría con potencias de 2 
	}
	
	/**Calcula el fitness de cada individuo y devuelve el numero de soluciones validas*/
	public int calcularFuncion(List<element> fenotipos) {
		int k = 0;
		int sol [] = new int [numVariables];
		return generar(k, sol, fenotipos);
	}
	
	/**Genera y devuelve el numero de soluciones validas*/
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
	
	/**Elige el mejor fitness de entre dos dados*/
	public boolean best(double fitness, double fitness2) {
		if(adaptador.getAdaptado()) {
			return fitness < fitness2;
		}
		else {
			return fitness > fitness2;
		}
	}

	
	/**Genera la lista de los individuos a los que se aplicará elite*/
	public void addElite(List<individuo> out, List<individuo> in, double tamElite) {
		for(int i=0; i < tamElite; i++)
			out.add(new individuo(in.get(i)));
	}

	
	private adaptacion getAdaptador() {
		return adaptador;
	}
	
}
