package br.upe.dsc.pso.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ChartDirector.Chart;
import ChartDirector.ChartViewer;
import ChartDirector.SurfaceChart;

public class Teste3D {
	public String toString() {
		return "Surface Chart (1)";
	}

	// Number of charts produced in this demo
	public int getNoOfCharts() {
		return 1;
	}

	// Main code for creating charts
	public void createChart(ChartViewer viewer, int index) {
		// The x and y coordinates of the grid
		
//		double[] dataX = new double[31];
//		double[] dataY = new double[31];
//		
//		for (int i = -15, j = 0; i < 16; i++, j++) {
//			dataX[j] = i;
//			dataY[j] = i;
//		}
		
		double[] dataX = { -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3,
				4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		double[] dataY = { -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3,
				4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

		// The values at the grid points. In this example, we will compute the
		// values
		// using the formula z = x * sin(y) + y * sin(x).
		double[] dataZ = new double[(dataX.length) * (dataY.length)];
		int i = 0;
		for (int yIndex = 0; yIndex < dataY.length; ++yIndex) {
			double y = dataY[yIndex];
			for (int xIndex = 0; xIndex < dataX.length; ++xIndex) {
				double x = dataX[xIndex];
				dataZ[yIndex * (dataX.length) + xIndex] = 
					(3*(Math.pow(1-x,2))*Math.exp(-(Math.pow(x,2))-(Math.pow(y+1,2)))-10*((x/5)-(Math.pow(x,3))-Math.pow(y,5))*Math.exp(-Math.pow(x,2)-Math.pow(y,2))-(1/3)*Math.exp(-(Math.pow(x+1, 2))-Math.pow(y,2)));

			}
		}

		// Create a SurfaceChart object of size 720 x 600 pixels
		SurfaceChart c = new SurfaceChart(720, 600);

		// Add a title to the chart using 20 points Times New Roman Italic font
		c.addTitle("Surface Energy Density   ", "Times New Roman Italic", 20);

		// Set the center of the plot region at (350, 280), and set width x
		// depth x
		// height to 360 x 360 x 270 pixels
		c.setPlotRegion(350, 280, 360, 360, 270);

		// Set the data to use to plot the chart
		c.setData(dataX, dataY, dataZ);

		// Spline interpolate data to a 80 x 80 grid for a smooth surface
		c.setInterpolation(80, 80);

		// Add a color axis (the legend) in which the left center is anchored at
		// (645, 270). Set the length to 200 pixels and the labels on the right
		// side.
		c.setColorAxis(645, 270, Chart.Left, 200, Chart.Right);

		// Set the x, y and z axis titles using 10 points Arial Bold font
		c.xAxis().setTitle("X (nm)", "Arial Bold", 10);
		c.yAxis().setTitle("Y (nm)", "Arial Bold", 10);
		c.zAxis().setTitle("Energy Density (J/m<*font,super*>2<*/font*>)",
				"Arial Bold", 10);

		// Output the chart
		viewer.setImage(c.makeImage());
	}

	// Allow this module to run as standalone program for easy testing
	public static void main(String[] args) {
		// Instantiate an instance of this demo module
		Teste3D demo = new Teste3D();

		// Create and set up the main window
		JFrame frame = new JFrame(demo.toString());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().setBackground(Color.white);

		// Create the chart and put them in the content pane
		ChartViewer viewer = new ChartViewer();
		demo.createChart(viewer, 0);
		frame.getContentPane().add(viewer);

		// Display the window
		frame.pack();
		frame.setVisible(true);
	}
}
