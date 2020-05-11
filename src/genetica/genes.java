package genetica;

import java.util.ArrayList;
import java.util.List;

import init.Completa;
import init.Creciente;
import init.RampedHalf;
import init.initMethod;
import model.arbol;
import model.contador;
import model.element;
import model.funcion;
import model.terminal;

public class genes {
	
	private arbol genotipo; 
	private List<element> fenotipo;
	private initMethod algInit;
	private int numVars;
	
	public genes(initMethod algInit, int numVars) {
		this.algInit=algInit;
		this.numVars = numVars;
		inicializarArbol(numVars);
		fenotipo=new ArrayList<element>();
		genotipo.representa(fenotipo);
	}

	public genes(genes gen) {
		numVars = gen.numVars;
		fenotipo = copiarFenotipo(gen.getFenotipoList());
		genotipo = new arbol(gen.getGenotipo());
		String tipo = gen.getInit().getTipo();
		if(tipo.equalsIgnoreCase("completa")) {
			algInit = new Completa(gen.getInit());
		}else if(tipo.equalsIgnoreCase("creciente")) {
			algInit = new Creciente(gen.getInit());
		}else if(tipo.equalsIgnoreCase("ramped")) {
			algInit = new RampedHalf((RampedHalf)gen.getInit());
		}
		
	}
	
	private List<element> copiarFenotipo(List<element> fenotipoList) {
		List<element> fenotipo = new ArrayList<element>();
		for (int i = 0; i < fenotipoList.size(); i++) {
			if(fenotipoList.get(i).getTipo().equalsIgnoreCase("funcion")) {
				fenotipo.add(new funcion(fenotipoList.get(i)));
			}
			else {
				fenotipo.add(new terminal(fenotipoList.get(i)));
			}
			
		}
		return fenotipo;
	}

	public void inicializarArbol(int numVars) {
		genotipo=algInit.crearArbol(numVars);
	}
	
	/**Convierte el la lista de elementos en un fenotipo tipo String*/
	public String visualizarFenotipo() {
		String resultado="";
		contador c=new contador();
		resultado+=fenotipo.get(0).toString(c, fenotipo);
		return resultado;
	}
	
	public List<element> getFenotipoList() {
		return fenotipo;
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

	public int getNumVars() {
		return numVars;
	}

	public initMethod getInit() {
		return algInit;
	}
}
