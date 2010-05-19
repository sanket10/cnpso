package br.upe.dsc.pso;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ChartDirector.ChartViewer;
import br.upe.dsc.pso.algorithm.GlobalBestPSO;
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
		
		GlobalBestPSO pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 2.0, swarmObserver);
		runChart(pso);
		
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 1.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
//		
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 0.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
//		
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 1.0, 2.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
//		
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 1.0, 1.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
//
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 1.0, 0.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
//		
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 0.0, 2.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
//		
//		pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 0.0, 1.0, swarmObserver);
//		fileUtil.createFile();
//		swarmObserver.reset();
//		runChart(pso);
		
//		 runSimple(pso);
//		runChart(pso);
		
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
		ChartView chart;
		
		// Create and set up the main window
		JFrame frame = new JFrame("PSO");
		frame.getContentPane().setBackground(Color.white);
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
		
		// The x and y coordinates of the chart grid
		double[] dataX = new double[31];
		double[] dataY = new double[31];
		
		for (int i = 0; i < 31; i++) {
			dataX[i] = i;
			dataY[i] = i;
		}
		
		// Instantiate an instance of this chart
		chart = new ChartView(frame, pso.getSwarmObserver(), dataX, dataY);

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
		
		// Gets the last image of the chart
		Image image = chart.getViewer().getImage();
		
		// Creates a BufferdImage from the Image
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);  
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);  
		g2.dispose();
		
		// Prints the image to a file
		pso.getSwarmObserver().getFileUtil().printImage(bufferedImage);
		
		chart.setRunning(false);
		
		frame.dispose();
	}
}
