package model;

public class arbol<T> {
	public static enum T {IF, AND, NOT, OR}
	private T elemento=null;
	private arbol<T> izq=null;
	private arbol<T> der=null;
	private arbol<T> cen=null;
	int numNodos;
	int profundidad;
	 
	public arbol() {}
	public arbol(T elemento) {
		this.elemento=elemento;
		numNodos = 1;
		profundidad = 1;
	}
	public arbol(T elemento, arbol<T>izq) {
		this.elemento=elemento;
		this.izq=izq;
		numNodos = izq.numNodos++;
		profundidad = izq.profundidad++;
	}
	
	public arbol(T elemento, arbol<T>izq, arbol<T>der) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
		numNodos = izq.numNodos + der.numNodos + 1;
		if(izq.profundidad > der.profundidad)profundidad = izq.profundidad++;
		else profundidad = der.profundidad++;
		
	}
	public arbol(T elemento, arbol<T>izq, arbol<T>der, arbol<T>cen) {
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
	
	public arbol(arbol<T> old) {
		elemento=old.getElemento();
		izq=old.getIzq();
		der=old.getDer();
		cen=old.getCen();
		profundidad = old.profundidad;
		numNodos = old.numNodos;
	}
	
	public arbol<T> getCen() {
		return cen;
	}
	
	public arbol<T> getDer() {
		return der;
	}
	
	public arbol<T> getIzq() {
		return izq;
	}
	
	public T getElemento() {
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
	
	public void setIzq(arbol<T> izq) {
		this.izq=izq;
		numNodos++;
		//profundidad ??
	}
	
	public void setDer(arbol<T> der) {
		this.der=der;
		//profundidad ??
		numNodos++;
	}
	
	public void setCen(arbol<T> cen) {
		this.cen=cen;
		numNodos++;
		//profundidad ??
	}
}
