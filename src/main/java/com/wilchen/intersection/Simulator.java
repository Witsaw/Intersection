package com.wilchen.intersection;

/*
 *  Name   : Simulator
 *  Author : William Chen
 */

public class Simulator {

	public static void main(String[] args) {
		int max = 1000;
		if (args.length < 1) {
			System.out.println("Warning: Missing max duration. Will use 1000 as default.");
		} else {
			try {
				max = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Max duration should be integer, Please fix.");
				System.exit(0);
			}
		}
		try {
			Intersection intersection = new Intersection(max);

			intersection.run();

			intersection.printStats();
		} catch (Exception e) {
			System.err.println("Run into error: " + e.getMessage());
		}
	}
}