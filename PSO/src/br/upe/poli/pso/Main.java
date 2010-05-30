package br.upe.poli.pso;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ChartDirector.ChartViewer;
import br.upe.poli.pso.algorithm.GlobalBestPSO;
import br.upe.poli.pso.algorithm.LocalBestPSO;
import br.upe.poli.pso.algorithm.PSO;
import br.upe.poli.base.Problem;
import br.upe.poli.pso.problems.ProblemFactory;
import br.upe.poli.pso.problems.RandomPeaks;
import br.upe.poli.pso.util.ChartImage;
import br.upe.poli.pso.util.FileUtil;
import br.upe.poli.pso.view.ChartView;
import br.upe.poli.pso.view.SwarmObserverPSO;

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
		
		String problemName = "RandomPeaks";
		
		// Get the problem
		ProblemFactory problems = ProblemFactory.getInstance();
		Problem problem = problems.getProblem(problemName);

		// Code to create the chart image
		createChartImage(problem);
		
		
		// Code to open the frame and see the simulation
		
		FileUtil fileUtil = new FileUtil(problemName);
		int swarmSize = 30;
		
		SwarmObserverPSO swarmObserver = new SwarmObserverPSO(swarmSize, problem, fileUtil);

//		GlobalBestPSO pso = new GlobalBestPSO(swarmSize, 100, 0.01, problem, 2.0, 2.0, swarmObserver);
		
		//GlobalBestPSO pso = new GlobalBestPSO(swarmSize, 100, 0.01, problem, 2.0, 0.2, swarmObserver);
		
		// GBest
		//GlobalBestPSO pso = new GlobalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 2.0, swarmObserver);
		
		// LBest
		//LocalBestPSO pso = new LocalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 2.0, swarmObserver);
		
		// GBest Social
		LocalBestPSO pso = new LocalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 2.0, swarmObserver);
		
		// LBest Cognitivo
		//LocalBestPSO pso = new LocalBestPSO(swarmSize, 500, 0.01, problem, 2.0, 0.1, swarmObserver);
		
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
		
		fileUtil.end();
	}

	private static void runSimple(PSO pso) {
		FileUtil fileUtil = ((SwarmObserverPSO) pso.getSwarmObserver()).getFileUtil();
		int iterationsNumber = 30;
		
		for (int i = 0; i < iterationsNumber; i++) {
			pso.execute();
			
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
		chart = new ChartView(frame, (SwarmObserverPSO) pso.getSwarmObserver(), dataX, dataY);
		
		// Create the chart and put them in the content pane
		chart.setViewer(new ChartViewer());
		chart.createChart(chart.getViewer(), chart.getSwarmObserver());
		frame.getContentPane().add(chart.getViewer());

		// Display the window
		frame.pack();
		frame.setVisible(true);
		
		// Runs the algorithm
		chart.setRunning(true);
		new Thread(chart).start();
		pso.execute();
		
		// Gets the last image of the chart
		Image image = chart.getViewer().getImage();
		
		// Creates a BufferdImage from the Image
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);  
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);  
		g2.dispose();
		
		// Prints the image to a file
		((SwarmObserverPSO) pso.getSwarmObserver()).getFileUtil().printImage(bufferedImage);
		
		chart.setRunning(false);
		
		frame.dispose();
	}
	
	public static void createChartImage(Problem problem) {
		String simulationFilePath = "Random_Peaks/19052010_090457";
		String simulationFileName = "090457.txt";
		int iteration = 10;
		
		ChartImage chartImage = new ChartImage(simulationFilePath, simulationFileName, "teste2", iteration, problem);
		chartImage.createImage();
	}
}
