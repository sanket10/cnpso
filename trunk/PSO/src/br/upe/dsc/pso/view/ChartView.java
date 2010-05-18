package br.upe.dsc.pso.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ChartDirector.Chart;
import ChartDirector.ChartViewer;
import ChartDirector.ColorAxis;
import ChartDirector.ContourLayer;
import ChartDirector.XYChart;
import br.upe.dsc.pso.algorithm.LocalBestPSO;
import br.upe.dsc.pso.problems.PeaksProblem;
import br.upe.dsc.pso.problems.IProblem;

public class ChartView implements Runnable {
	
	private SwarmObserver swarmObserver;
	private ChartViewer viewer;
	private boolean running;
	private JFrame frame;
	
	// Name of demo program
	public String toString() {
		return "Continuous Contour Coloring";
	}

	// Number of charts produced in this demo
	public int getNoOfCharts() {
		return 1;
	}

	// Main code for creating charts
	public void createChart(ChartViewer viewer, int index, SwarmObserver swarmObserver) {
	
		double[] dataX = new double[31];
		double[] dataY = new double[31];
		
		for (int i = 0; i < 31; i++) {
			dataX[i] = i;
			dataY[i] = i;
		}
		// The x and y coordinates of the grid
//		double[] dataX = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
//		double[] dataY = { -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3,
//				4, 5, 6, 7, 8, 9, 10 };

		// The values at the grid points. In this example, we will compute the
		// values using the formula z = Sin(x / 2) * Sin(y / 2).
		double[] dataZ = new double[(dataX.length) * (dataY.length)];
		for (int yIndex = 0; yIndex < dataY.length; ++yIndex) {
			double y = dataY[yIndex];
			for (int xIndex = 0; xIndex < dataX.length; ++xIndex) {
				double x = dataX[xIndex];
//				dataZ[yIndex * (dataX.length) + xIndex] = 418.9829 * 2 + (-x * Math.sin(Math.sqrt(Math.abs(x))) -y * Math.sin(Math.abs(y)));
//				dataZ[yIndex * (dataX.length) + xIndex] = (3*(Math.pow(1-x,2))*Math.exp(-(Math.pow(x,2))-(Math.pow(y+1,2)))-10*((x/5)-(Math.pow(x,3))-Math.pow(y,5))*Math.exp(-Math.pow(x,2)-Math.pow(y,2))-(1/3)*Math.exp(-(Math.pow(x+1, 2))-Math.pow(y,2)));
				
				dataZ[yIndex * (dataX.length) + xIndex] = +5*Math.exp(-0.1*((x-15)*(x-15)+(y-20)*(y-20)))
		        -2*Math.exp(-0.08*((x-20)*(x-20)+(y-15)*(y-15)))
		        +3*Math.exp(-0.08*((x-25)*(x-25) +(y-10)*(y-10)))
		        +2*Math.exp(-0.1*((x-10)*(x-10)+(y-10)*(y-10)))
		        -2*Math.exp(-0.5*((x-5)*(x-5)+(y-10)*(y-10)))
		        -4*Math.exp(-0.1*((x-15)*(x-15)+(y-5)*(y-5)))
		        -2*Math.exp(-0.5*((x-8)*(x-8)+(y-25)*(y-25)))
		        -2*Math.exp(-0.5*((x-21)*(x-21)+(y-25)*(y-25)))
		        +2*Math.exp(-0.5*((x-25)*(x-25)+(y-16)*(y-16)))
		        +2*Math.exp(-0.5*((x-5)*(x-5)+(y-14)*(y-14)));
			}
		}

		// Create a XYChart object of size 600 x 500 pixels
		XYChart c = new XYChart(600, 500);

		// Add a title to the chart using 18 points Times New Roman Bold Italic
		// font
		c.addTitle("PSO",
				"Times New Roman Bold Italic", 18);

		// Set the plot area at (75, 40) and of size 400 x 400 pixels. Use
		// semi-transparent black (80000000) dotted lines for both horizontal
		// and vertical grid lines
		c.setPlotArea(75, 30, 400, 400, -1, -1, -1, c.dashLineColor(0x80000000,
				Chart.DotLine), -1);

		// Set x-axis and y-axis title using 12 points Arial Bold Italic font
		c.xAxis().setTitle("Lattice X Direction (nm)", "Arial Bold Italic", 12);
		c.yAxis().setTitle("Lattice Y Direction (nm)", "Arial Bold Italic", 12);

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
		cAxis.setTitle("Twist Pressure (Twist-Volt)", "Arial Bold Italic", 12);

		// Set color axis labels to use Arial Bold font
		cAxis.setLabelStyle("Arial Bold");

		// Use smooth gradient coloring
		cAxis.setColorGradient(true);

		// Output the chart
		viewer.setImage(c.makeImage());
	}

	@Override
	public void run() {
		while (running){
			createChart(viewer, 0, swarmObserver);
			frame.repaint();
			try {
				Thread.sleep(500);
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