package init;

import model.*;

public class Completa extends initMethod{

	@Override
	public arbol crearArbol(int profundidad, int numVars) {
		arbol nodo=null;
		//Crea un nodo función
		if(profundidad > 0) {
			nodo=new arbol(new funcion(useIfs()));
			nodo.setIzq(crearArbol(profundidad-1, numVars));
			if(!nodo.getElemento().getValor().equalsIgnoreCase("NOT")) {
				nodo.setDer(crearArbol(profundidad-1, numVars));
			}
			if(useIfs() && nodo.getElemento().getValor().equalsIgnoreCase("IF")) {
				nodo.setCen(crearArbol(profundidad-1, numVars));
			}
			
		}
		//Crea un nodo terminal
		else {
			nodo=new arbol(new terminal(numVars));
		}
		return nodo;
	}	
}