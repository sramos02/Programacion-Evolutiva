package bloating;

import poblacion.poblacion;

public class BienFundamentado extends Bloating{
	public BienFundamentado() {
		super();
	}
	@Override
	public void aplicarBloating(poblacion poblacion) {
		double varianza = Probabilidad.varianza(poblacion);
		if(varianza > 0) {
			setK(Probabilidad.covarianza(poblacion)/varianza);
			int media = Probabilidad.calcularProfMedia(poblacion);
			for(int i=0; i < poblacion.getSize(); i++) {
				double prof = poblacion.getProfundidad(i);
				if(prof > media) {
					int fit = (int) (poblacion.getIndividuo(i).getFitness() -
							getK() * prof);
					if(fit < 0) {
						fit = 0;
					}
					poblacion.getIndividuo(i).setFitness(fit);
				}
			}
		}
	}
	
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
