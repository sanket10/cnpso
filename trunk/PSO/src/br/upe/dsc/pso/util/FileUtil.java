package br.upe.dsc.pso.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileUtil {
	private FileWriter file;
	private String problemDirectoryName;
	private String executionDirectoryName;
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
		String name = s.format(new Date()) + ".txt";
		
		try {
			
			// If we have an instance of File, we can have a file opened.
			// In that case, we try to close that file before we create a new one.
			if (file != null) {
				file.close();
			}
			
			file = new FileWriter(new File(filePath, name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
