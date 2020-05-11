package bloating;
import poblacion.poblacion;

public class Probabilidad {
	public static double varianza(poblacion poblacion) {
		double resultado=0;
		int media = calcularProfMedia(poblacion);
		int sumatorio=0;
		int size = poblacion.getSize();
		for (int i = 0; i < size; i++) {
			int profundida = poblacion.getProfundidad(i);
			sumatorio += Math.pow((profundida - media), 2);
		}
		resultado = sumatorio/size;
		return resultado;
	}
	
	public static double covarianza(poblacion poblacion) {
		double resultado = 0;
		int prof_media = calcularProfMedia(poblacion);
		int fitnes_medio = calcularFitnessMedio(poblacion);
		int sumatorio = 0;
		int size = poblacion.getSize();
		for (int i = 0; i < size; i++) {
			int profundidad = poblacion.getProfundidad(i);
			int fit = poblacion.getFitness(i);
			sumatorio += profundidad * fit;
		}
		resultado = sumatorio/size - fitnes_medio*prof_media;
		return resultado;
	}
	
	private static int calcularFitnessMedio(poblacion poblacion) {
		int total = 0;
		for (int i = 0; i < poblacion.getSize(); i++) {
			total += poblacion.getFitness(i);
		}
		return (total/poblacion.getSize());
	}
	
	public static int calcularProfMedia(poblacion poblacion) {
		int total = 0;
		for (int i = 0; i < poblacion.getSize(); i++) {
			total += poblacion.getProfundidad(i);
		}
		return (total/poblacion.getSize());
	}
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
