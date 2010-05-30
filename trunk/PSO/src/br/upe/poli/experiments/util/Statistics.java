package br.upe.poli.experiments.util;

import java.util.Vector;

/**
 * @author Danilo
 * 
 */
public class Statistics {

	public static Double calculateMedian(Vector<Double> vector, int first,
			int last) {
		double median = 0.0;

		int size = last - first + 1;

		if (size % 2 != 0) {
			median = (Double) vector.elementAt(first + size / 2);
		} else {
			median = ((Double) vector.elementAt(first + size / 2 - 1) + (Double) vector
					.elementAt(first + size / 2)) / 2.0;
		}

		return median;
	}

	public static Double calculateIQR(Vector<Double> vector) {
		double q3 = 0.0;
		double q1 = 0.0;

		if (vector.size() > 1) {
			if (vector.size() % 2 != 0) {
				q3 = calculateMedian(vector, vector.size() / 2 + 1, vector
						.size() - 1);
				q1 = calculateMedian(vector, 0, vector.size() / 2 - 1);
			} else {
				q3 = calculateMedian(vector, vector.size() / 2,
						vector.size() - 1);
				q1 = calculateMedian(vector, 0, vector.size() / 2 - 1);
			}
		}

		return q3 - q1;
	}
	
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
