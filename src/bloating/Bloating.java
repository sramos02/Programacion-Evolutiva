package bloating;

import poblacion.poblacion;

public abstract class Bloating {
	private int k;
	private double probabilidad = 0.5;

	public Bloating() {}

	public abstract void aplicarBloating(poblacion poblacion);
	
	public double getProbabilidad() {
		return probabilidad;
	}
	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}
}
