package model;

import java.util.List;

public class arbol {
	private element elemento=null;
	private arbol izq=null;
	private arbol der=null; 
	private arbol cen=null;
	int numNodos;
	int profundidad;
	int aux;
	
	public arbol() {}
	
	public arbol(element elemento) {
		this.elemento=elemento;
		numNodos = 1;
		profundidad = 1;
		this.aux = 0;
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
	

	private int getNumeroNodos() {
		return numNodos;
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
	
	public void setAux(int a) {
		aux = a;
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
		return izq==null && der==null && cen==null;
	}
	
	public void setIzq(arbol izq) {
		this.izq=izq;
		numNodos+=izq.getNumeroNodos();
		int nueva = izq.profundidad+1;
		int profundidad1=cen!=null?cen.getProfundidad():0;
		int profundidad2=der!=null?der.getProfundidad():0;
		profundidad=getMaxProfundidad(nueva, profundidad1, profundidad2);
	}
	
	public void setDer(arbol der) {
		this.der=der;
		numNodos+=der.getNumeroNodos();
		int nueva = der.profundidad+1;
		int profundidad1=cen!=null?cen.getProfundidad():0;
		int profundidad2=izq!=null?izq.getProfundidad():0;
		profundidad=getMaxProfundidad(nueva, profundidad1, profundidad2);
	}
	
	public void setCen(arbol cen) {
		this.cen=cen;
		numNodos+=cen.getNumeroNodos();
		int nueva = cen.profundidad+1;
		int profundidad1=der!=null?der.getProfundidad():0;
		int profundidad2=izq!=null?izq.getProfundidad():0;
		profundidad=getMaxProfundidad(nueva, profundidad1, profundidad2);
	}
	
	public int getMaxProfundidad(int valor1, int valor2, int valor3) {
		int maxProfundidad=valor1;
		if(valor2 > maxProfundidad && valor2 > valor3) {
			return valor2;
		}
		return valor3 > maxProfundidad ? valor3 : maxProfundidad;
	}
	
	public void setProfundidad(int prof) {
		profundidad = prof;
	}
	
	public void setNumElem(int n) {
		numNodos = n;
	}
	
	public void representa(List<element> ret) {
		ret.add(elemento);
		if(izq != null) {
			izq.representa(ret);
		} 
		if(der != null) {
			der.representa(ret);
		} 
		if(cen != null) {
			cen.representa(ret);
		} 
	}
	

	public void setNodoArbol(element valor, int pos) {
		this.aux = 0;
		setNodoAux(valor, pos + 1);
	}

	private void setNodoAux(element valor, int pos) {
		aux++;
		if(pos == aux) elemento = valor;
		else {
			if(izq != null) {
				izq.aux = aux;
				izq.setNodoAux(valor, pos);
				aux = izq.aux;
			}
			if(der != null ) {
				der.aux = aux;
				der.setNodoAux(valor, pos);
				aux = der.aux;
			} 
			if(cen != null) {
				cen.aux = aux;
				cen.setNodoAux(valor, pos);
				aux = cen.aux;
			} 		
		}
	}

}
