package com.algocw;

/**
 * Author: S.P.Imasha Udayangi
 * Student ID: 20221357
 * UOW ID: w1956115
 */

import com.algocw.GameMap;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Print the header information
        System.out.println("***********************************");
        System.out.println("Finding Shortest Path to Maps");
        System.out.println("***********************************");
        System.out.println("\tB.Benchmark_series");
        System.out.println("\tE.Examples");
        System.out.println("Select the folder from above:-");

        // Create a Scanner object to read user input
        Scanner InputFile = new Scanner(System.in);

        String input = null;
        // Loop until a valid input (B or E) is provided
        while (input == null || (!input.equalsIgnoreCase("B") && !input.equalsIgnoreCase("E"))) {
            input = InputFile.nextLine();
            if (!input.equalsIgnoreCase("B") && !input.equalsIgnoreCase("E")) {
                System.out.println("Invalid input. Please enter B or E.");
            }
        }

        // Determine the filename based on the user's input
        String fileName = null;
        if (input.equalsIgnoreCase("B")){
            fileName="benchmark_series/";
        }
        if(input.equalsIgnoreCase("E")){
            fileName="examples/";
        }

        // Prompt the user to enter a filename
        System.out.println("Enter filename: ");
        String file = InputFile.nextLine();
        // Append the filename with .txt extension if necessary
        if (file.contains(".txt")){
            fileName = fileName+ file;
        }else {
            fileName= fileName + file + ".txt";
        }
        try {
            // Parse the map from the input file
            GameMap map = SlidingPuzzle.parseMapFromFile(fileName);

            // Capture the start time
            long startTime = System.nanoTime();

            // Find the shortest path
            SlidingPuzzle.findShortestPath(map);

            // Capture the end time
            long endTime = System.nanoTime();

            // Calculate and print the duration
            long duration = (endTime - startTime) / 1_000_000;  // convert to milliseconds
            System.out.println();
            System.out.println("Time taken to find the shortest path: " + duration + " ms");
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File not found: " + fileName);
        } catch (Exception e) {
            // Handle other exceptions
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}