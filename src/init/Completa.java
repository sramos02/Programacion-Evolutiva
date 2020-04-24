package init;

import java.util.Random;
import model.*;

public class Completa extends initMethod{

	@Override
	public arbol crearArbol(int profundidad) {
		arbol nodo=null;
		//Crea un nodo función
		if(profundidad > 0) {
			nodo=new arbol(new funcion(useIfs()));
			nodo.setIzq(crearArbol(profundidad-1));
			if(nodo.getElemento().getValor() != "NOT") {
				nodo.setDer(crearArbol(profundidad-1));
			}
			if(useIfs() && nodo.getElemento().getValor() == "IF") {
				nodo.setCen(crearArbol(profundidad-1));
			}
			
		}
		//Crea un nodo terminal
		else {
			nodo=new arbol(new terminal());
		}
		return nodo;
	}

	
	/*@Override
	public void inicia(arbol tree, int prof_min, int prof_max, boolean useIfs) {
	
		Random rand = new Random();
		
		if(prof_min > 0) { //No puede ser hoja
			T operador = escogeTipoAleatorio(useIfs, rand);
			//tree.dato = operador;
			
			tree.setIzq(construir_arbol(operador, tree.getIzq(), prof_min - 1, prof_max - 1));
			tree.setNumElem(tree.numElem() + tree.getIzq().numElem()); 
			
			tree.setDer(construir_arbol(operador, tree.getDer(), prof_min - 1, prof_max - 1));
			tree.setNumElem(tree.numElem() + tree.getDer().numElem());
			
			if(useIfs) {
				tree.setCen(construir_arbol(operador, tree.getCen(), prof_min - 1, prof_max - 1));
				tree.setNumElem(tree.numElem() + tree.getCen().numElem()); 
			}
		}
		
		if (prof_max == 0) { // sólo puede ser hoja
			T operador = escogeTipoAleatorio(useIfs, rand);
			//tree.dato = operador;
			tree.setNumElem(tree.numElem() + 1);
		}
		
		 boolean tipo = rand.nextBoolean();
		 if(tipo) { //Operador
			 // generación del subarbol de operador
		 }
		 else{ //Operando
			//generación del subarbol de operando
		 } 
	}

		
	private arbol construir_arbol(element operador, arbol izq, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	private T escogeTipoAleatorio(boolean useIfs, Random rand) {
		int valor = rand.nextInt(T.values().length);
		while(!useIfs && valor == 0) rand.nextInt(T.values().length); 
		
		return T.values()[valor];
	}*/

}
