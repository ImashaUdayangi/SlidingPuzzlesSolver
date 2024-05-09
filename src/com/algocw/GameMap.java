package com.algocw;

/**
 * Author: S.P.Imasha Udayangi
 * Student ID: 20221357
 * UOW ID: w1956115
 */

public class GameMap {
    public int height; // Height of the game map
    public int width;// Width of the game map
    public Node start; // Node representing the starting position
    public Node finish;// Node representing the finishing position
    public char[][] grid;// 2D grid representing the game map

    /**
     * Constructor for the GameMap class.
     *
     * @param height The height of the game map.
     * @param width The width of the game map.
     */
    public GameMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new char[height][width];// Initialize the grid with the specified dimensions
    }
}
