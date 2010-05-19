package br.upe.dsc.pso.util;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

public class FileUtil {
	private FileWriter file;
	private String problemDirectoryName;
	private String executionDirectoryName;
	private String fileName;
	private String filePath;
	
	public FileUtil(String problemName) {
		createProblemDirectory(problemName);
		createExecutionDirectory();
		
		filePath = problemDirectoryName + File.separatorChar + executionDirectoryName + File.separatorChar;
		createFile();
	}
	
	public void printIterationHeader(int iteration) {
		try {
			file.write("\nIteration " + iteration + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printPosition(double x, double y, double z) {
		try {
			file.write(x + " " + y + " " + z + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printImage(RenderedImage image) {
		try {
			ImageIO.write(image, "PNG", new File(filePath, fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createProblemDirectory(String problemName) {
		problemDirectoryName = problemName.replace(" ", "_");
		
		File directory = new File(problemDirectoryName);
		if (!directory.isDirectory()) {
			directory.mkdir();
		}
	}
	
	public void end() {
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createExecutionDirectory() {
		SimpleDateFormat s = new SimpleDateFormat("ddMMyyyy_hhmmss");
		
		executionDirectoryName = s.format(new Date());
		File directory = new File(problemDirectoryName + File.separatorChar + executionDirectoryName);
		if (!directory.isDirectory()) {
			directory.mkdir();
		}
	}
	
	public void createFile() {
		SimpleDateFormat s = new SimpleDateFormat("hhmmss");
		fileName = s.format(new Date());
		
		try {
			
			// If we have an instance of File, we can have a file opened.
			// In that case, we try to close that file before we create a new one.
			if (file != null) {
				file.close();
			}
			
			file = new FileWriter(new File(filePath, fileName + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
