package model;

import java.util.List;
import java.util.Random;

public class funcion extends element{
	private String [] posiblesValores= {"AND", "OR", "NOT", "IF"};
	private int size=4;
	
	public funcion(boolean useIfs) {
		setTipo("funcion");
		generarFuncion(useIfs);
	}
	
	/**Elige uno de los posibles valores(AND, OR, NOT o If) y lo devuelve*/
	public void generarFuncion(boolean useIfs) {
		int realSize=useIfs?4:3;
		Random r=new Random();
		int pos=Math.abs(r.nextInt()%realSize);
		setValor(posiblesValores[pos]);
	}

	@Override
	public String toString(contador i, List<element> fenotipo) {
		String cadena="";
		cadena="(";
		cadena+=" "+fenotipo.get(i.getCount()).getValor();
		i.addCount();
		cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
		if(getValor() == "IF") {
			cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
			cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
		}
		else if(getValor() != "NOT"){
			cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
		}
		cadena+=" )";
		
		return cadena;
	}
	
	
}
