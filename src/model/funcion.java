package model;

import java.util.List;
import java.util.Random;

public class funcion extends element{
	private String [] posiblesValores= {"AND", "OR", "NOT", "IF"};
	private int size;
	
	public funcion(boolean useIfs) {
		if(useIfs)size = 3;
		else size = 4;
		setTipo("funcion");
		generarFuncion();
	}
	
	/**Elige uno de los posibles valores(AND, OR, NOT o If) y lo devuelve*/
	public void generarFuncion() {
		Random r=new Random();
		int pos=Math.abs(r.nextInt()%size);
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

	@Override
	protected int evaluarExpresion(contador contador, int[] sol, List<element> fenotipo) {
		int op1, op2;
		
		contador.addCount();
		switch(getValor()) {
		case "NOT":
			return fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo)==1? 0 : 1;
		case "AND":
			op1=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			op2=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			return op1 & op2;
		case "OR":
			op1=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			op2=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			return op1 | op2;
		case "IF":
			int condicion = fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			op1=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			op2=fenotipo.get(contador.getCount()).evaluarExpresion(contador, sol, fenotipo);
			return condicion == 1 ? op1:op2 ;
		}
		return 0; //Aqui solo llega en caso de error
	}
	
}
