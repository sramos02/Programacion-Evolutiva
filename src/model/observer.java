package model;

public interface observer {
	public void onFinished(int mejorResultado, double[][] best, double[][] bestGen, double[][] average, String bestResult, int mejorEsperado, int mejorPos);
	public void onNextGeneration();
}
