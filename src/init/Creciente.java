package init;

import java.util.Random;

import model.arbol;
import model.funcion;
import model.terminal;

public class Creciente extends initMethod{

	@Override
	public arbol crearArbol(int profundidad, int numVars) {
		arbol nodo=null;
		Random r=new Random();
		
		//Crea un nodo funcion
		if(profundidad > 0) {
			//Elegimos del conjunto completo entre terminal y funcion
			boolean useTerminal = r.nextBoolean();
		
			if(useTerminal) {
				nodo=new arbol(new terminal(numVars));
			}
			else {
				nodo=new arbol(new funcion(useIfs()));
				nodo.setIzq(crearArbol(profundidad-1, numVars));
				
				if(nodo.getElemento().getValor() != "NOT") 
					nodo.setDer(crearArbol(profundidad-1, numVars));
				if(useIfs() && nodo.getElemento().getValor() == "IF") 
					nodo.setCen(crearArbol(profundidad-1, numVars));		
			}
		}
		//Crea un nodo terminal
		else nodo=new arbol(new terminal(numVars));
		return nodo;
	}



}
