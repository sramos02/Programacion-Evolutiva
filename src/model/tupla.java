package model;

public class tupla {
	private int posicion;
	private String valor;
	
	public tupla(int posicion, String valor) {
		this.posicion=posicion;
		this.valor=valor;
	}

	public tupla(tupla old) {
		posicion = old.getPosicion();
		valor = old.getValor();
				
	}

	public String getValor() {
		return valor;
	}
	
	public int getPosicion() {
		return posicion;
	}
}
