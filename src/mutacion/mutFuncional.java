package mutacion;

import java.util.List;
import java.util.Random;

import model.element;
import model.funcion;
import poblacion.poblacion;

public class mutFuncional extends mutacion{

	@Override
	public void mutar(poblacion poblacion, double probMutacion) {
		
		double prob = Math.random()%1;
		for(int i = 0; i < poblacion.getSize(); i++) {
			if(prob > probMutacion){
				System.out.println(i);
			}
		}
	}
}