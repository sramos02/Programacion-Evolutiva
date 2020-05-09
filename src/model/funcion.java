package model;

import java.util.List;
import java.util.Random;

public class funcion extends element{
	private final String [] posiblesValores= {"AND", "OR", "NOT", "IF"};
	private final int size=4;
	
	public funcion(boolean useIfs) {
		setTipo("funcion");
		generarFuncion(useIfs);
	}
	
	public funcion(element elemento) {
		super(elemento);
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
		if(getValor().equalsIgnoreCase("IF")) {
			cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
			cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
		}
		else if(!getValor().equalsIgnoreCase("NOT")){
			cadena+=" "+fenotipo.get(i.getCount()).toString(i, fenotipo);
		}
		cadena+=" )";
		
		return cadena;
	}

	@Override
	protected int evaluarExpresion(contador contador, int[] sol, List<element> fenotipo) {
		int solucion=0;
		int a, b;
		contador.addCount();
		if(getValor().equalsIgnoreCase("NOT")) {
			solucion=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo)==1? 0 : 1;
		}else if(getValor().equalsIgnoreCase("AND")){
			a=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			b=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			solucion=a & b;
		}else if(getValor().equalsIgnoreCase("OR")) {
			a=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			b=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			solucion=a | b;
		}else if(getValor().equalsIgnoreCase("IF")) {
			int condicion=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			a=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			b=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			solucion = condicion == 1 ? a:b ;
		}
		return solucion;
	}

	public String nuevaFuncion() {
		Random r=new Random();
		return posiblesValores[Math.abs(r.nextInt()%size)];
		
	}

	public int numOperandos() {
		
		if(getValor().equalsIgnoreCase("NOT")) {
			return 1;
		}
		else if(getValor().equalsIgnoreCase("AND") || getValor().equalsIgnoreCase("OR")) {
			return 2;
		} else if(getValor().equalsIgnoreCase("IF")){
			return 3;
		}
		return 0;
	}
	
}
