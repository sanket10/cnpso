package br.upe.dsc.pso;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ChartDirector.ChartViewer;
import br.upe.dsc.pso.algorithm.LocalBestPSO;
import br.upe.dsc.pso.algorithm.PSO;
import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.problems.RandomPeaksProblem;
import br.upe.dsc.pso.util.FileUtil;
import br.upe.dsc.pso.view.ChartView;
import br.upe.dsc.pso.view.SwarmObserver;

/**
 * @author marlon
 * 
 */
public class Main {

	public static void main(String[] args) {
		// IProblem problem = new Problem1();
		// IProblem problem = new Problem2();
		// IProblem problem = new Problem3();
		// IProblem problem = new Problem4();
		// IProblem problem = new Problem5();
		// IProblem problem = new Problem6();
		// IProblem problem = new PeaksProblem();
		
		IProblem problem = new RandomPeaksProblem();
		FileUtil fileUtil = new FileUtil(problem.getName());
		int swarmSize = 30;
		
		SwarmObserver swarmObserver = new SwarmObserver(swarmSize, problem, fileUtil);

//		GlobalBestPSO pso = new GlobalBestPSO(swarmSize, 100, 0.01, problem, 2.0, 2.0, swarmObserver);
		LocalBestPSO pso = new LocalBestPSO(swarmSize, 100, 0.01, problem, 2.0, 2.0, swarmObserver);

//		 runSimple(pso);
		runChart(pso);
		
		fileUtil.end();
	}

	private static void runSimple(PSO pso) {
		FileUtil fileUtil = pso.getSwarmObserver().getFileUtil();
		int iterationsNumber = 30;
		
		for (int i = 0; i < iterationsNumber; i++) {
			pso.run();
			
			if (i < iterationsNumber - 1) {
				fileUtil.createFile();
			}
		}
	}

	public static void runChart(PSO pso) {

		// Create and set up the main window
		JFrame frame = new JFrame("PSO");
		frame.getContentPane().setBackground(Color.white);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// The x and y coordinates of the chart grid
		double[] dataX = new double[31];
		double[] dataY = new double[31];
		
		for (int i = 0; i < 31; i++) {
			dataX[i] = i;
			dataY[i] = i;
		}
		
		// Instantiate an instance of this chart
		ChartView chart = new ChartView(frame, pso.getSwarmObserver(), dataX, dataY);

		// Create the chart and put them in the content pane
		chart.setViewer(new ChartViewer());
		chart.createChart(chart.getViewer(), 0, chart.getSwarmObserver());
		frame.getContentPane().add(chart.getViewer());

		// Display the window
		frame.pack();
		frame.setVisible(true);

		// Runs the algorithm
		chart.setRunning(true);
		new Thread(chart).start();
		pso.run();
		chart.setRunning(false);
	}
}
