package bloating;

import poblacion.poblacion;

public class Tarpeian extends Bloating{
	private int k=2;
	
	public Tarpeian() {
		super();
		setK(k);
	}
	
	
	//Elimina aquellos programas de longitud mayor que la media
	@Override
	public void aplicarBloating(poblacion poblacion) {
		int media = calcularProfMedia(poblacion);
		
		for(int i=0; i < poblacion.getSize(); i++) {
			int profundidad = poblacion.getProfundidad(i);
			double valor = Math.random()%1;
			if(profundidad > media && valor <= getProbabilidad()) {
				poblacion.getIndividuo(i).setFitness((int) (poblacion.getIndividuo(i).getFitness() - getK() * poblacion.getProfundidad(i)));
			}
		}
	}
	
	private int calcularProfMedia(poblacion poblacion) {
		int total = 0;
		for (int i = 0; i < poblacion.getSize(); i++) {
			total += poblacion.getProfundidad(i);
		}
		return (total/poblacion.getSize());
	}

}
