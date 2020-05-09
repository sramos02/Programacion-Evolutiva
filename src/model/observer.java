package model;

public interface observer {
	public void onFinished(double[][] best, double[][] bestGen, double[][] average, String bestResult, int mejorEsperado, int mejorPos);
	public void onNextGeneration();
}
