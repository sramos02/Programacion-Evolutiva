package model;

import java.util.List;

public class arbol {
	private element elemento=null;
	private arbol izq=null;
	private arbol der=null; 
	private arbol cen=null;
	private algoritmo algoritmo;
	int numNodos;
	int profundidad;
	
	public arbol() {}
	public arbol(element elemento) {
		this.elemento=elemento;
		numNodos = 1;
		profundidad = 1;
	}
	public arbol(element elemento, arbol izq) {
		this.elemento=elemento;
		this.izq=izq;
		numNodos = izq.numNodos++;
		profundidad = izq.profundidad++;
	}
	public arbol(element elemento, arbol izq, arbol der) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
		numNodos = izq.numNodos + der.numNodos + 1;
		if(izq.profundidad > der.profundidad)profundidad = izq.profundidad++;
		else profundidad = der.profundidad++;
		
	}
	public arbol(element elemento, arbol izq, arbol der, arbol cen) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
		this.cen=cen;
		numNodos = izq.numNodos + der.numNodos + cen.numNodos;
		
		int prof = der.profundidad;
		if(izq.profundidad > der.profundidad)prof = izq.profundidad;
		if(cen.profundidad > prof)prof = cen.profundidad++;
		profundidad = prof;
	}
	public arbol(arbol old) {
		elemento=old.getElemento();
		izq=old.getIzq();
		der=old.getDer();
		cen=old.getCen();
		profundidad = old.profundidad;
		numNodos = old.numNodos;
	}
	
	/**Devuelve un nodo aleatorio del arbol del tipo indicado.
	 * Valores del tipo:
	 * 						-Nodo -> cualquier nodo del arbol.
	 * 						-Funcion -> nodo funci�n.
	 * 						-Terminal -> nodo terminal.*/
	arbol getNodoAleatorio(String tipo) {
		arbol nodo=null;
		int size=getNumeroNodos(tipo);
		switch(tipo) {
		case "Nodo":
			break;
		case "Funcion":
			break;
		case "Terminal":
			break;
		}
		return nodo;
	}
	
	/**Devuelve el n�mero de nodos que hay del tipo indicado*/
	private int getNumeroNodos(String tipo) {
		int size=0;
		
		return size;
	}
	public arbol getCen() {
		return cen;
	}
	
	public arbol getDer() {
		return der;
	}
	
	public arbol getIzq() {
		return izq;
	}
	
	public element getElemento() {
		return elemento;
	}
	
	public int getProfundidad() {
		return profundidad;
	}
	
	public int numElem() {
		return numNodos;
	}
	
	public boolean empty() {
		return elemento==null;
	}
	
	public boolean esHoja() {
		return izq.empty() && der.empty() && cen.empty();
	}
	
	public void setIzq(arbol izq) {
		this.izq=izq;
		numNodos++;
		int nueva = this.izq.profundidad + izq.profundidad;
		if(nueva >= cen.profundidad && nueva >= der.profundidad) profundidad = nueva;
	}
	
	public void setDer(arbol der) {
		this.der=der;
		numNodos++;
		int nueva = this.der.profundidad + der.profundidad;
		if(nueva >= cen.profundidad && nueva >= izq.profundidad) profundidad = nueva;
	}
	
	public void setCen(arbol cen) {
		this.cen=cen;
		numNodos++;
		int nueva = this.cen.profundidad + cen.profundidad;
		if(nueva >= izq.profundidad && nueva >= der.profundidad) profundidad = nueva;
	}
	
	public void setProfundidad(int prof) {
		profundidad = prof;
	}
	public void setNumElem(int n) {
		numNodos = n;
	}
	public void representa(List<element> ret) {
		if(esHoja()) ret.add(elemento);
		else {
			if(der != null) der.representa(ret);
			if(izq != null) izq.representa(ret);
			if(cen != null) cen.representa(ret);
		}
	}
}
