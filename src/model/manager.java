 package model;

import java.util.ArrayList;
import java.util.List;
import cruces.*;
import init.Completa;
import init.Creciente;
import init.RampedHalf;
import init.initMethod;
import mutacion.Arbol;
import mutacion.Permutacion;
import mutacion.mutFuncional;
import mutacion.mutTerminal;
import mutacion.mutacion;
import poblacion.individuo;
import poblacion.poblacion;
import seleccion.*;

public class manager {

	private int valorMejor;
	private int mejorPos;
	private List<observer> observers;
	
	private algoritmoSeleccion algSel;
	private algoritmoCruce algCruce;		
	private mutacion algMut;
	private initMethod algInit;
	private poblacion poblacion;
	private double bestGen [][];
	private double average [][];
	private double best [][];
	private String bestExpresion;
	private algoritmo funcion;
	private double probElite;
	private double probCruc;
	private double probMut;	
	private int generation;
	private elite elite;
	private int maxIter;
	private int tamPob;
	private boolean useIfs;
	private int numVariables;

	public manager() {
		observers=new ArrayList<observer>();
		funcion=new algoritmo();
		elite=new elite();
		iniciarDatos();
	}
	public void iniciarDatos() {
		mejorPos=0;
		probElite=0.05;
		generation=0;
		probCruc=0.6;
		probMut=0.02;
		maxIter=100;
		tamPob=100;
		numVariables=6;
		useIfs = false;
	}
	public void addObserver(observer o) {
		if(!observers.contains(o)) {
			observers.add(o);
		}
	}

	public void iniciarPoblacion() {
		algInit.setUseIfs(useIfs);
		funcion.setNumVariables(numVariables);
		poblacion=new poblacion(tamPob, funcion, algInit, numVariables);
		poblacion.iniciarPoblacion();
		best=new double[2][maxIter];
		bestGen=new double[2][maxIter];
		average=new double[2][maxIter];
		
	}
	public void start() {
		generation=0;
		iniciarPoblacion();
		evaluarPoblacion();
		generation++;
		while(generation < maxIter) {
			elite.escogerElites(poblacion, probElite);
			adaptar();
			seleccion();
			desadaptar();
			reproduccion();
			mutacion();
			elite.incluirElites(poblacion);
			evaluarPoblacion();
			generation++;
		}
		
		for(int i=0; i < observers.size(); i++) {
			observers.get(i).onFinished( best, bestGen, average, bestExpresion, valorMejor, mejorPos);
		}
	}
	private void desadaptar() {
		funcion.desadaptar(poblacion);
	}
	private void adaptar() {
		funcion.adaptar(poblacion);
	}
	private void seleccion() {
		poblacion=algSel.ini(poblacion, funcion);
	}
	private void mutacion() {
		algMut.mutar(poblacion, probMut);
	}
	private void evaluarPoblacion() {
		evaluarMejor();
		evaluarMedia();
	}
	private void evaluarMedia() {
		average[0][generation]=generation;
		average[1][generation]=poblacion.getAverage();
	}
	
	private void evaluarMejor() {
		best[0][generation]=generation;
		bestGen[0][generation]=generation;
		bestGen[1][generation]=poblacion.getBest();
		if(generation==0 || funcion.best(bestGen[1][generation], best[1][generation-1])) {
			best[1][generation]=bestGen[1][generation];
			mejorPos=generation;
			individuo mejor=poblacion.getMejorInd();
			bestExpresion=mejor.getExpresion();
		}
		else
		{
			best[1][generation]=best[1][generation-1];
		}
	}
	private void reproduccion() {
		if(algCruce!=null) {
			algCruce.cruzar(poblacion, probCruc);
		}else {
			System.out.println("No se ha inicializado el algoritmo de cruce");
		}
	}
	
	public void establerMetodoSeleccion(int metodo) {
		switch(metodo)
		{
		case 0: algSel=new algoritmoRuleta();
			break;
		case 1: algSel=new algoritmoTorneoDeter();
			break;
		case 2: algSel=new algoritmoTorneoProb();
			break;
		case 3: algSel=new algoritmoEstocasticoUniv();
			break;
		case 4: algSel=new algoritmoTruncamiento();
			break;
		case 5: algSel=new algoritmoRanking();
			break;
		case 6: algSel = new algotirmoOtro();
			break;
		}
	}
	public double[][] getBest() {
		return best;
	}
	public double[][] getBestGen() {
		return bestGen;
	}
	public double[][] getAverage() {
		return average;
	}
	public void setPopulationSize(int popSize) {
		tamPob=popSize;
	}

	public void setGenerationNumber(int genNum) {
		maxIter=genNum;
	}
	public void setProbCruce(double d) {
		probCruc=d;
	}
	public void setCrossFunct(int i) {
		switch(i) {
		case 0: algCruce = new Intercambio();
			break;
		}
	}
	
	public void setMutationFunct(int i) {
		switch(i) {
		case 0: algMut=new Arbol();
			break;
		case 1: algMut = new mutTerminal();
			break;
		case 2: algMut = new mutFuncional();
			break;
		case 3: algMut = new Permutacion();
		break;
		}
	}
	public void setInitFunct(int i) {
		switch(i) {
		case 0: algInit = new Completa();
		break;
		case 1: algInit = new RampedHalf();
		break;		
		case 2: algInit = new Creciente();
		break;
		}	
	}
	public void setMutationPercent(double mutPer) {
		probMut=mutPer;
	}
	public void setElitePercent(double d) {
		probElite=d;
	}
	public void reset() {
		iniciarDatos();
	}
	
	public void setObservers(List<observer> obs) {
		observers=new ArrayList<observer>();
		for(int i=0; i < obs.size(); i++) {
			observers.add(obs.get(i));
		}
	}
	public void useIfs(boolean b) {
		useIfs = b;
	}
	public void setNumVars(int v) {
		numVariables=v;
	}
}

