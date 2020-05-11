package model;

import java.util.List;

public abstract class element {
	private String valorPropio;
	private String tipo;
	
	public element() {}
	public element(element old) {
		tipo = old.getTipo();
		valorPropio = old.getValor();
	}

	public void setTipo(String t) {
		tipo=t;
	}
	
	public String getTipo() {
		return new String(tipo);
	}
	public String getValor() {
		return new String(valorPropio);
	}
	
	public void setValor(String valor) {
		valorPropio=valor;
	}

	public abstract String toString(contador i, List<element> fenotipo);

	protected abstract int evaluarExpresion(contador contador, int[] sol, List<element> fenotipo);

}
