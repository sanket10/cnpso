package br.upe.dsc.pso.view;

import java.awt.Image;

import javax.swing.JFrame;

import ChartDirector.Chart;
import ChartDirector.ChartViewer;
import ChartDirector.ColorAxis;
import ChartDirector.ContourLayer;
import ChartDirector.XYChart;
import br.upe.dsc.pso.problems.IProblem;

public class ChartView implements Runnable {
	
	private SwarmObserver swarmObserver;
	private ChartViewer viewer;
	private boolean running;
	private JFrame frame;
	private double[] dataX;
	private double[] dataY;
	private IProblem problem;
	
	public ChartView(JFrame frame, SwarmObserver swarmObserver, double[] dataX, double[] dataY) {
		this.frame = frame;
		this.swarmObserver = swarmObserver;
		this.dataX = dataX;
		this.dataY = dataY;
		this.problem = swarmObserver.getProblem();
	}
	
	// Name of the chart
	public String toString() {
		return "PSO";
	}

	// Number of charts produced
	public int getNoOfCharts() {
		return 1;
	}

	public Image createChartImage(SwarmObserver swarmObserver) {
		// The values at the grid points. In this example, we will compute the
		// values using the formula z = Sin(x / 2) * Sin(y / 2).
		double[] dataZ = new double[(dataX.length) * (dataY.length)];
		for (int yIndex = 0; yIndex < dataY.length; ++yIndex) {
			double y = dataY[yIndex];
			for (int xIndex = 0; xIndex < dataX.length; ++xIndex) {
				double x = dataX[xIndex];
				
				dataZ[yIndex * (dataX.length) + xIndex] = problem.getFitness(new double[]{x,y});
			}
		}

		// Create a XYChart object of size 600 x 500 pixels
		XYChart c = new XYChart(600, 500);

		// Add a title to the chart using 18 points Times New Roman Bold Italic
		// font
		c.addTitle(problem.getName(), "Times New Roman Bold Italic", 18);

		// Set the plot area at (75, 40) and of size 400 x 400 pixels. Use
		// semi-transparent black (80000000) dotted lines for both horizontal
		// and vertical grid lines
		c.setPlotArea(75, 40, 400, 400, -1, -1, -1, c.dashLineColor(0x80000000,
				Chart.DotLine), -1);

		// Set x-axis and y-axis title using 12 points Arial Bold Italic font
		c.xAxis().setTitle("x", "Arial Bold Italic", 12);
		c.yAxis().setTitle("y", "Arial Bold Italic", 12);

		// Set x-axis and y-axis labels to use Arial Bold font
		c.xAxis().setLabelStyle("Arial Bold");
		c.yAxis().setLabelStyle("Arial Bold");

		// When auto-scaling, use tick spacing of 40 pixels as a guideline
		c.yAxis().setTickDensity(40);
		c.xAxis().setTickDensity(40);
		
		// Add a contour layer using the given data
		ContourLayer layer = c.addContourLayer(dataX, dataY, dataZ);

		// Set the contour color to transparent
		layer.setContourColor(Chart.Transparent);

		// Move the grid lines in front of the contour layer
		c.getPlotArea().moveGridBefore(layer);
		
		c.addScatterLayer(swarmObserver.getXAxis(), swarmObserver.getYAxis(), "", Chart.Cross2Shape(0.2), 7, 0x000000);
		
		// Add a color axis (the legend) in which the left center point is anchored
		// at (495, 240). Set the length to 370 pixels and the labels on the right side.
		ColorAxis cAxis = layer.setColorAxis(495, 240, Chart.Left, 370,
				Chart.Right);

		// Add a bounding box to the color axis using light grey (eeeeee) as the
		// background and dark grey (444444) as the border.
		cAxis.setBoundingBox(0xeeeeee, 0x444444);

		// Add a title to the color axis using 12 points Arial Bold Italic font
		cAxis.setTitle("Height", "Arial Bold Italic", 12);

		// Set color axis labels to use Arial Bold font
		cAxis.setLabelStyle("Arial Bold");

		// Use smooth gradient coloring
		cAxis.setColorGradient(true);
		return c.makeImage();
	}
	
	// Main code for creating charts
	public void createChart(ChartViewer viewer, SwarmObserver swarmObserver) {

		// Output the chart
		Image image = createChartImage(swarmObserver);
		viewer.setImage(image);
	}

	@Override
	public void run() {
		while (running){
			createChart(viewer, swarmObserver);
			frame.repaint();
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {}
		}
	}

	public SwarmObserver getSwarmObserver() {
		return swarmObserver;
	}

	public void setSwarmObserver(SwarmObserver swarmObserver) {
		this.swarmObserver = swarmObserver;
	}

	public ChartViewer getViewer() {
		return viewer;
	}

	public void setViewer(ChartViewer viewer) {
		this.viewer = viewer;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}