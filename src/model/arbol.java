package model;

import java.util.List;

import init.initMethod;

public class arbol {
	private element elemento;
	private arbol izq;
	private arbol der; 
	private arbol cen;
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
		setVariables(old);
	}
	

	private void setVariables(arbol old) {
		elemento=null;
		izq=null;
		der=null; 
		cen=null;
		if(old.getElemento().getTipo().equalsIgnoreCase("funcion")) {
			elemento = new funcion(old.getElemento());
			izq= old.getIzq() == null ? null : new arbol(old.getIzq());
			der= old.getDer() == null ? null : new arbol(old.getDer());
			cen= old.getCen() == null ? null : new arbol(old.getCen());
		}
		else {
			elemento = new terminal(old.getElemento());
		}
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
		int profundidad1=cen!=null?cen.getProfundidad()+1:1;
		int profundidad2=der!=null?der.getProfundidad()+1:1;
		profundidad=getMaxProfundidad(nueva, profundidad1, profundidad2);
	}
	
	public void copiaIzq(arbol i) {
		izq = i;
	}
	
	public void copiaDer(arbol d) {
		der = d;
	}

	public void setDer(arbol der) {
		this.der=der;
		numNodos+=der.getNumeroNodos();
		int nueva = der.profundidad+1;
		int profundidad1=cen!=null?cen.getProfundidad()+1:1;
		int profundidad2=izq!=null?izq.getProfundidad()+1:1;
		profundidad=getMaxProfundidad(nueva, profundidad1, profundidad2);
	}
	
	public void setCen(arbol cen) {
		this.cen=cen;
		numNodos+=cen.getNumeroNodos();
		int nueva = cen.profundidad+1;
		int profundidad1 = der != null ? der.getProfundidad()+1 : 1;
		int profundidad2 = izq != null ? izq.getProfundidad()+1 : 1;
		profundidad=getMaxProfundidad(nueva, profundidad1, profundidad2);
	}
	
	/**Devuelve la profundidad máxima de entre 3 arboles*/
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
	
	public void mutarNodo(initMethod ini, int numVars) {
		while(!mutarSubArbol(ini, numVars));
	}
	
	private boolean mutarSubArbol(initMethod ini, int numVars) {
		boolean elegido = false;
		double valor = Math.random()%1;
		double prob = 0.5;
		if(valor <= prob) {
			setVariables(ini.crearArbol(profundidad-1, numVars));
			elegido = true;
		}else {
			if(izq != null) {
				elegido = izq.mutarSubArbol(ini, numVars);
			}
			if( der != null && !elegido) {
				elegido = der.mutarSubArbol(ini, numVars);
			}
			if( cen  != null && !elegido) {
				elegido = cen.mutarSubArbol(ini, numVars);
			}
		}	
		recalcularPropiedades();
		return elegido;
	}

	public static void intercambiarNodos(double prob_func, double prob_terminal, arbol hijo1, arbol hijo2) {
		arbol nodo=null;
		prob_func = 1;
		prob_terminal = 0;
		
		while(nodo == null) {
			nodo = hijo1.getNodoAleatorio(prob_func, prob_terminal, hijo2);
		}
	}
	
	private arbol getNodo2Aleatorio(double prob_func, double prob_terminal, arbol nodo1) {
		arbol nodo=null;	

		boolean elegido = false;
		double valor = Math.random()%1;
		double prob = elemento.getTipo().equalsIgnoreCase("funcion") ? prob_func : prob_terminal;

		if(valor <= prob/2) {
			nodo= new arbol(this);
			elegido = true;
		}
		else {
			if(izq != null) {
				nodo = izq.getNodo2Aleatorio(prob_func, prob_terminal, nodo1);
			}
			if( der != null && nodo == null) {
				nodo = der.getNodo2Aleatorio(prob_func, prob_terminal, nodo1);
			}
			if( cen  != null && nodo == null) {
				nodo = cen.getNodo2Aleatorio(prob_func, prob_terminal, nodo1);
			}
		}
		if(nodo != null && elegido) {
			//Sustituir nodo2 por nodo1
			setVariables(nodo1);
		}

		recalcularPropiedades();
		return nodo;
	}

	/**Vuelve a calcular la profundidad y el numero de nodos de este arbol*/
	private void recalcularPropiedades() {
		recalcularNumNodos();
		recalcularProfundidad();
	}

	/**Vuelve a calcular la profundidad máxima del arbol*/
	private void recalcularProfundidad() {
		int izq_prof = izq != null ? izq.getProfundidad()+1 : 1;
		int cen_prof = cen != null ? cen.getProfundidad()+1 : 1;
		int der_prof = der != null ? der.getProfundidad()+1 : 1;
		profundidad=getMaxProfundidad(izq_prof, der_prof, cen_prof);
	}

	/**Vuelve a calcular el numero de nodos totales del arbol*/
	private void recalcularNumNodos() {
		numNodos = 1;
		numNodos += izq != null ? izq.getNumeroNodos() : 0;
		numNodos += der != null ? der.getNumeroNodos() : 0; 
		numNodos += cen != null ? cen.getNumeroNodos() : 0; 
	}

	private arbol getNodoAleatorio(double prob_func, double prob_terminal, arbol hijo2) {
		arbol nodo=null;
		boolean elegido = false;
		
		double valor = Math.random()%1;
		double prob = elemento.getTipo().equalsIgnoreCase("funcion") ? prob_func : prob_terminal;
		
		if(valor <= prob/2) {
			nodo = new arbol(this);
			elegido = true;
		}
		else {
			if(izq != null) {
				nodo = izq.getNodoAleatorio(prob_func, prob_terminal, hijo2);
			}
			if( der != null && nodo == null) {
				nodo = der.getNodoAleatorio(prob_func, prob_terminal, hijo2);
			}
			if( cen  != null && nodo == null) {
				nodo = cen.getNodoAleatorio(prob_func, prob_terminal, hijo2);
			}
		}
		
	
		if(nodo != null && elegido) {
			arbol nodo2=null;
			while(nodo2 == null) {
				nodo2 = hijo2.getNodo2Aleatorio(prob_func, prob_terminal, nodo);
			}
			setVariables(nodo2);
		}
		
		recalcularPropiedades();
		return nodo;
	}

	private arbol encontrarNodo(int r) {
		contador i = new contador();
		return encontrarNodo(r,i);
	}

	private arbol encontrarNodo(int r, contador i) {
		arbol nodo=null;
		if(r == i.getCount()) {
			nodo = this;
		}else {
			
			if(izq != null) {
				i.addCount();
				nodo = izq.encontrarNodo(r, i);
			}
			if(der != null && nodo == null) {
				i.addCount();
				nodo = der.encontrarNodo(r, i);
			}
			if(cen != null && nodo == null) {
				i.addCount();
				nodo = cen.encontrarNodo(r, i);
			}
		}
		return nodo;
	}

	public void permutarHijos(int r) {
		arbol padre = encontrarNodo(r);
		arbol aux = new arbol(padre.getIzq());
		padre.copiaIzq(new arbol(padre.getDer()));
		padre.copiaDer(new arbol(aux));
		//padre.recalcularPropiedades();
	}
}
