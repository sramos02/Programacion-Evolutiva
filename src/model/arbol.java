package model;

public class arbol<T> {
	private T elemento=null;
	private arbol<T> izq=null;
	private arbol<T> der=null;
	private arbol<T> cen=null;
	
	public arbol() {}
	public arbol(T elemento) {
		this.elemento=elemento;
	}
	public arbol(T elemento, arbol<T>izq) {
		this.elemento=elemento;
		this.izq=izq;
	}
	public arbol(T elemento, arbol<T>izq, arbol<T>der) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
	}
	public arbol(T elemento, arbol<T>izq, arbol<T>der, arbol<T>cen) {
		this.elemento=elemento;
		this.izq=izq;
		this.der=der;
		this.cen=cen;
	}
	public arbol(arbol<T> old) {
		elemento=old.getElemento();
		izq=old.getIzq();
		der=old.getDer();
		cen=old.getCen();
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
	
	public boolean empty() {
		return elemento==null;
	}
	
	public void setIzq(arbol<T> izq) {
		this.izq=izq;
	}
	
	public void setDer(arbol<T> der) {
		this.der=der;
	}
	
	public void setCen(arbol<T> cen) {
		this.cen=cen;
	}
}
