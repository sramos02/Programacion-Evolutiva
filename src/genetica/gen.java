package genetica;

import model.arbol;

public class gen {
	
	private arbol genotipo; 
	private String fenotipo;
	
	public gen() {}
	public gen(arbol valor) {
		genotipo=valor;
		fenotipo = valor.representa();
	}
	
	public gen(gen gen) {
		fenotipo = gen.getFenotipo();
		genotipo=gen.getGenotipo();
	}
	
	public int getSizeGenotipo(){
		return genotipo.getProfundidad();
	}
	
	public String getFenotipo() {
		return fenotipo;
	}
	
	public arbol getGenotipo() {
		return genotipo;
	}
	public void setFenotipo(String d) {
		fenotipo = d;
		//Crea arbol
	}
}
