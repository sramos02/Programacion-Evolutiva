package genetica;

import java.util.ArrayList;
import java.util.List;

import init.initMethod;
import model.arbol;
import model.element;

public class genes {
	
	private arbol genotipo; 
	private List<element> fenotipo;
	private initMethod algInit;
	
	public genes(initMethod algInit) {
		this.algInit=algInit;
		inicializarArbol();
		fenotipo=new ArrayList<element>();
		genotipo.representa(fenotipo);
	}

	public genes(genes gen) {
		fenotipo = gen.getFenotipo();
		genotipo=gen.getGenotipo();
		algInit=gen.getAlgInit();
	}
	
	private void inicializarArbol() {
		//genotipo=algInit.inicia(tree, prof_min, prof_max, useIfs);
	}
	
	private initMethod getAlgInit() {
		return algInit;
	}

	public int getSizeGenotipo(){
		return genotipo.getProfundidad();
	}
	
	public List<element> getFenotipo() {
		return new ArrayList<element>(fenotipo);
	}
	
	public arbol getGenotipo() {
		return genotipo;
	}
	
	public void setFenotipo(List<element> d) {
		fenotipo = d;
	}
	
	public int size() {
		return genotipo.numElem();
	}
}
