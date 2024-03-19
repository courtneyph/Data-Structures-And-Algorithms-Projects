/**
 * A program to profile sine approximation algorithms that use
 * the Taylor series expansion of the sine function: 
 * sine(x) = x - x^3/3! + x^5/5! - x^7/7! + x^9/9! .....   
 * @author William Duncan, Courtney Pham
 * @see SineUtil
 * <pre>
 * Date: September, 1, 2023
 * Course: csc 3102
 * Project # 0
 * Instructor: Dr. Duncan
 * </pre>
 */

import java.util.Scanner;

public class SineProfiler
{
	public static void main(String[] args) 
	{
        Scanner in = new Scanner(System.in);
		System.out.println("Please enter an angle in radians.");
		double angle = in.nextDouble();
		double nativeSine = SineUtil.naiveSine(angle, 100);
		double fastSine = SineUtil.fastSine(angle, 100);
		
		System.out.printf("Naive Sin of (%.4f) = %.4f\n", angle, nativeSine);
		System.out.printf("Fast Sine of (%.4f) = %.4f\n\n", angle, fastSine);

		System.out.printf("%-7s %-13s %15s \n\n", "n", "Naive Sine(us)", "Fast Sine(us)");


	long start;
	long elapsed;

	for(int i = 1000; i <= 15000; i += 1000) {

		System.out.printf("%-11d",i);
		start = System.nanoTime();
		SineUtil.naiveSine(angle, i);
		elapsed = System.nanoTime() - start;
		System.out.printf("%-17d", elapsed / 1000);
		start = System.nanoTime();
		SineUtil.fastSine(angle, i);
		elapsed = System.nanoTime() - start;
		System.out.printf("%-15d", elapsed / 1000);
		System.out.println();
	} 
}}
