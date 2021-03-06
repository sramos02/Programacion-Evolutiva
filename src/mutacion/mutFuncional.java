package mutacion;

import java.util.List;
import java.util.Random;

import model.element;
import model.funcion;
import poblacion.poblacion;

public class mutFuncional extends mutacion{

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {

		for(int i = 0; i < poblacion.getSize(); i++) {
			double prob = Math.random()%1;			
			if(prob < probMutacion){

				poblacion.getIndividuo(i).calcularFenotipo();
				Random rand = new Random();
				List<element> fenotipoMutado = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				int r = rand.nextInt(fenotipoMutado.size());

				//Se selecciona al azar una funci�n dentro del individuo,
				//y se sustituye por otra diferente del conjunto de funciones 
				//posibles con el mismo n�mero de operandos

				while(fenotipoMutado.get(r).getTipo().equalsIgnoreCase("terminal")) 
					r = rand.nextInt(fenotipoMutado.size());

				element nuevo = new funcion(poblacion.getUseIfs());	
				funcion aux2 = (funcion) nuevo;

				while(aux2.numOperandos() != ((funcion) fenotipoMutado.get(r)).numOperandos()) {
					//while(aux2.getValor().equalsIgnoreCase(fenotipoMutado.get(r).getValor())) {
					nuevo.setValor(aux2.nuevaFuncion());
					aux2 =  (funcion) nuevo;						
				}

				//Cambiamos el genotipo y el fenotipo
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().setNodoArbol(nuevo, r);
				//Calculamos el nuevo Fitness
				poblacion.getIndividuo(i).calcularFenotipo();
				poblacion.getIndividuo(i).calcularFitness();
			}				
		}

	}
}







//for(int u = 0; u < fenotipoMutado.size(); u++) {
//	System.out.print(fenotipoMutado.get(u).getValor() + " ");
//}
//System.out.println();
