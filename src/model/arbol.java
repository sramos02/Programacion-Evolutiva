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
	
	
	
	
	//Tengo dudas sobre como está implementada la profundidad, creo que no está bien
	
	
	
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
	
	
	public String representa() {
		String ret = "";
		if(esHoja()) ret += elemento.toString();
		else {
			if(der != null) ret += der.representa();
			if(cen != null) ret += cen.representa();
			if(izq != null) ret += izq.representa();
		}
		
		return ret;
	}
}
