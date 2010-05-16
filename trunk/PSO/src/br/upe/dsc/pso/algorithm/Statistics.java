package br.upe.dsc.pso.algorithm;

public class Statistics {
	
	public static double getStandardDeviation(double[] data) {
		return Math.sqrt(getVariance(data));
	}
	
	public static double getVariance(double[] data) {
		double media = getArithmeticAverage(data);
		double sum1 = 0, sum2 = 0;
		
		for (double x : data) {
			sum1 += Math.pow((x - media), 2);
			sum2 += (x - media);
		}
		return (sum1 - (Math.pow(sum2, 2) / data.length)) / (data.length - 1);
	}
	
	public static double getArithmeticAverage(double[] data) {
		double sum = 0;
		for (double x : data) {
			sum += x;
		}
		return (sum / data.length);
	}

}
