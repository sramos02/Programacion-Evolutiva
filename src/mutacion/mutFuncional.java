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
				Random rand = new Random();
				List<element> fenotipoMutado = poblacion.getIndividuo(i).getCromosoma().getFenotipoList();
				int r = rand.nextInt(fenotipoMutado.size());
				
				
				//Se selecciona al azar una función dentro del individuo, 
				//y se sustituye por otra diferente del conjunto de funciones 
				//posibles con el mismo número de operandos
				while(fenotipoMutado.get(r).getTipo().equalsIgnoreCase("terminal")) {
			    	r = rand.nextInt(fenotipoMutado.size());
				}
			
				element nuevo = new funcion(poblacion.getUseIfs());	
				funcion aux2 = (funcion) nuevo;
				nuevo.setTipo("funcion");
				nuevo.setValor(aux2.nuevaFuncion());
				
				
				while(aux2.numOperandos() == ((funcion) poblacion.getIndividuo(i).getCromosoma().getFenotipoList().get(r)).numOperandos())
					nuevo.setValor(aux2.nuevaFuncion());	
				
				
				//Cambiamos el genotipo
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().setNodoArbol(nuevo, r);
				
				//Cambiamos el fenotipo
				poblacion.getIndividuo(i).getCromosoma().getGenotipo().representa(fenotipoMutado);
				poblacion.getIndividuo(i).getCromosoma().setFenotipo(fenotipoMutado);
			}
			poblacion.getIndividuo(i).calcularFenotipo();
			poblacion.getIndividuo(i).calcularFitness();
		}
	}
}