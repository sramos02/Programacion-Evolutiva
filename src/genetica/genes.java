package genetica;

import java.util.ArrayList;
import java.util.List;
import init.initMethod;
import model.arbol;
import model.contador;
import model.element;

public class genes {
	
	private arbol genotipo; 
	private List<element> fenotipo;
	private initMethod algInit;
	
	public genes(initMethod algInit, int numVars) {
		this.algInit=algInit;
		inicializarArbol(numVars);
		fenotipo=new ArrayList<element>();
		genotipo.representa(fenotipo);
	}

	public genes(genes gen) {
		fenotipo = gen.getFenotipoList();
		genotipo=gen.getGenotipo();
		algInit=gen.getAlgInit();
	}
	
	private void inicializarArbol(int numVars) {
		genotipo=algInit.crearArbol(numVars);
	}
	
	/**Convierte el la lista de elementos en un fenotipo tipo String*/
	public String visualizarFenotipo() {
		String resultado="";
		contador c=new contador();
		resultado+=fenotipo.get(0).toString(c, fenotipo);
		return resultado;
	}
	private initMethod getAlgInit() {
		return algInit;
	}

	public int getSizeGenotipo(){
		return genotipo.getProfundidad();
	}
	
	public List<element> getFenotipoList() {
		return new ArrayList<element>(fenotipo);
	}
	
	public arbol getGenotipo() {
		return genotipo;
	}
	
	public void setFenotipo(List<element> d) {
		fenotipo = d;
	}
	
	public String getFenotipo() {
		return visualizarFenotipo();
	}
	
	public int size() {
		return genotipo.numElem();
	}
}
