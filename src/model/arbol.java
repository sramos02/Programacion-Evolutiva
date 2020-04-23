package model;

public class arbol {
	
	private T elemento=null;
	private arbol izq=null;
	private arbol der=null;
	private arbol cen=null;
	int numNodos;
	int profundidad;
	 
	public arbol() {}
	public arbol(T elemento) {
		this.elemento=elemento;
		numNodos = 1;
		profundidad = 1;
	}
	public arbol(T elemento, arbol izq) {
		this.elemento=elemento;
		this.izq=izq;
		numNodos = izq.numNodos++;
		profundidad = izq.profundidad++;
	}
	
	public arbol(T elemento, arbol izq, arbol der) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
		numNodos = izq.numNodos + der.numNodos + 1;
		if(izq.profundidad > der.profundidad)profundidad = izq.profundidad++;
		else profundidad = der.profundidad++;
		
	}
	public arbol(T elemento, arbol izq, arbol der, arbol cen) {
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
	
	public arbol(arbol  old) {
		elemento=old.getElemento();
		izq=old.getIzq();
		der=old.getDer();
		cen=old.getCen();
		profundidad = old.profundidad;
		numNodos = old.numNodos;
	}
		
	public arbol getCen() {
		return cen;
	}
	
	public arbol  getDer() {
		return der;
	}
	
	public arbol getIzq() {
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
	
	public void setIzq(arbol izq) {
		this.izq=izq;
		numNodos++;
		//profundidad ??
	}
	
	public void setDer(arbol der) {
		this.der=der;
		//profundidad ??
		numNodos++;
	}
	
	public void setCen(arbol cen) {
		this.cen=cen;
		numNodos++;
		//profundidad ??
	}
	
	public void setProfundidad(int prof) {
		profundidad = prof;
	}
	
	public void setNumElem(int n) {
		numNodos = n;
	}
}
