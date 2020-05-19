package view;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import controller.controller;
import model.observer;

@SuppressWarnings("serial")
public class graphPanel extends JPanel implements observer {
	private controller ctrl;
	private Plot2DPanel plot;
	private JLabel fitness;
	private JTextField variables;
	public graphPanel(controller c) {
		ctrl=c;
		ctrl.addObserver(this);
		initGUI();
	}

	private void initGUI() {
		this.validate();
		this.repaint();
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		plot.setPreferredSize(new Dimension(700,600));
		plot.setMinimumSize(new Dimension(700,600));
		plot.setMaximumSize(new Dimension(700,600));
		this.add(plot);
		
		fitness=new JLabel("Fitness: ");
		variables=new JTextField("Mejor: ");
		variables.setMaximumSize(new Dimension(800, 60));
		variables.setMinimumSize(new Dimension(600, 60));
		variables.setPreferredSize(new Dimension(700, 60));
		this.add(new JScrollPane(variables));
		this.add(fitness);
		this.add(variables);
		this.setVisible(true);
		
	}
	
	@Override
	public void onFinished(double[][] best, double[][] bestGen, double[][] average, String bestResult, int mejorEsperado, int pos) {
		plot.removeAllPlots();
		plot.addLinePlot("Mejor absoluto", best[0], best[1]);
		plot.addLinePlot("Mejor generación", bestGen[0], bestGen[1]);
		plot.addLinePlot("Media generación", average[0], average[1]);
		fitness.setText("Pos: " + pos);
		variables.setText("Mejor: " + bestResult);
		this.repaint();
	}
	@Override
	public void onNextGeneration() {
		// TODO Auto-generated method stub
		
	}

}
