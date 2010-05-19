package br.upe.dsc.pso.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.upe.dsc.pso.problems.IProblem;
import br.upe.dsc.pso.view.ChartView;
import br.upe.dsc.pso.view.SwarmObserver;

public class ChartImage {
	private FileReader chartFile;
	private BufferedReader chartReader;
	
	private IProblem problem;
	private String simulationFilePath;
	private String simulationFileName;
	private String chartFileName;
	private int iteration;
	private int swarmSize;
	
	public ChartImage(String simulationFilePath, String simulationFileName, String chartFileName, int iteration, IProblem problem) {
		this.simulationFilePath = simulationFilePath;
		this.simulationFileName = simulationFileName;
		this.chartFileName = chartFileName;
		this.iteration = iteration;
		this.problem = problem;
		
		openFile();
		getSimulationConfiguration();
	}
	
	public void createImage() {
		double[] xAxis = new double[swarmSize];
		double[] yAxis = new double[swarmSize];
		
		boolean foundIterationStart = false;
		String line;
		int iterationNumber;
		
		try {
			
		// Searches for the beggining of the iteration
		while (!foundIterationStart) {
			
				line = chartReader.readLine();
			
				if (line.startsWith("Iteration")) {
					iterationNumber = Integer.parseInt(line.split(" ")[1].trim());
					
					foundIterationStart = (iterationNumber == iteration);
				}
			
		}
		
			// Once the beggining of the iteration was found, reads the particle's position
			line = chartReader.readLine();
			
			int i = 0;
			while (!line.startsWith("Iteration") && !line.startsWith("\n") && !line.equals("")) {
				xAxis[i] = Double.parseDouble(line.split(" ")[0].trim());
				yAxis[i] = Double.parseDouble(line.split(" ")[1].trim());
				i++;
				line = chartReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		double[] dataX = new double[31];
		double[] dataY = new double[31];
		
		for (int i = 0; i < 31; i++) {
			dataX[i] = i;
			dataY[i] = i;
		}
		
		SwarmObserver swarmObserver = new SwarmObserver(swarmSize, problem, null);
		swarmObserver.setXAxis(xAxis);
		swarmObserver.setYAxis(yAxis);
		ChartView chart = new ChartView(null, swarmObserver, dataX, dataY);
		Image image = chart.createChartImage(swarmObserver);
		
		// Creates a BufferdImage from the Image
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);  
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);  
		g2.dispose();
		
		// Prints the image to a file
		try {
			ImageIO.write(bufferedImage, "PNG", new File(simulationFilePath, chartFileName + ".png"));
			
			chartReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openFile() {
		try {
			chartFile = new FileReader(new File(simulationFilePath, simulationFileName));
			chartReader = new BufferedReader(chartFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void getSimulationConfiguration() {
		String line = "";
		
		while (!line.startsWith("Swarm size:")) {
			try {
				line = chartReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		swarmSize = Integer.parseInt(line.split(":")[1].trim());
	}
}
