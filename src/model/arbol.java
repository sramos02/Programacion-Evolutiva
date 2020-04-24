package model;

public class arbol {
	private element elemento=null;
	private arbol izq=null;
	private arbol der=null; 
	private arbol cen=null;
	private algoritmo algoritmo;
	
	public arbol() {}
	public arbol(element elemento) {
		this.elemento=elemento;
	}
	public arbol(element elemento, arbol izq) {
		this.elemento=elemento;
		this.izq=izq;
	}
	public arbol(element elemento, arbol izq, arbol der) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
	}
	public arbol(element elemento, arbol izq, arbol der, arbol cen) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
		this.cen=cen;
	}
	public arbol(arbol old) {
		elemento=old.getElemento();
		izq=old.getIzq();
		der=old.getDer();
		cen=old.getCen();
	}
	
	/**Devuelve un nodo aleatorio del arbol del tipo indicado.
	 * Valores del tipo:
	 * 						-Nodo -> cualquier nodo del arbol.
	 * 						-Funcion -> nodo función.
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
	
	/**Devuelve el número de nodos que hay del tipo indicado*/
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
	
	public boolean empty() {
		return elemento==null;
	}
	
	public void setIzq(arbol izq) {
		this.izq=izq;
	}
	
	public void setDer(arbol der) {
		this.der=der;
	}
	
	public void setCen(arbol cen) {
		this.cen=cen;
	}
	
}
