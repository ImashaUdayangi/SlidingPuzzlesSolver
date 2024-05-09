package com.algocw;

/**
 * Author: S.P.Imasha Udayangi
 * Student ID: 20221357
 * UOW ID: w1956115
 */

import com.algocw.GameMap;
import com.algocw.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SlidingPuzzle {
    /**
     * Parses a game map from a text file.
     *
     * @param fileName The name of the file containing the map.
     * @return A GameMap object representing the parsed map.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static GameMap parseMapFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));

        int height = 0, width = 0;
        Node start = null, finish = null;

        // Read the file to determine the map dimensions and starting/finishing positions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            width = line.length();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == 'S') {
                    start = new Node(height, i , null);
                } else if (ch == 'F') {
                    finish = new Node(height, i, null);
                }
            }
            height++;
        }

        scanner.close();

        GameMap map = new GameMap(height, width);
        map.start = start;
        map.finish = finish;

        // Read the map again to populate the grid
        scanner = new Scanner(new File(fileName));
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int col = 0; col < line.length(); col++) {
                map.grid[row][col] = line.charAt(col);
            }
            row++;
        }
        scanner.close();

        return map;
    }

    /**
     * Finds the shortest path from the start to the finish position on the game map.
     *
     * @param map The GameMap object representing the game map.
     */
    public static void findShortestPath(GameMap map) {

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right

        Node[][] visited = new Node[map.height][map.width];
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(c -> Math.abs(c.column - map.finish.column) + Math.abs(c.row - map.finish.row)));

        queue.offer(map.start);

        // priority queue
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // If the current node is the finish node, print the path and return
            if (current.column == map.finish.column && current.row == map.finish.row) {
                printPath(current);
                return;
            }

            for (int[] dir : dirs) {
                int newColumn = current.column;
                int newRow = current.row;

                // Move in the current direction as far as possible
                while (isValidMove(map, newColumn + dir[0], newRow + dir[1])) {
                    newColumn += dir[0];
                    newRow += dir[1];
                    // If the new position is the finish node, print the path and return
                    if (map.grid[newColumn][newRow] == 'F') {
                        printPath(new Node(newColumn, newRow, current));
                        return;
                    }
                }

                // If the new cell is unvisited, add it to the queue and mark it as visited
                if (visited[newColumn][newRow] == null) {
                    Node newCell = new Node(newColumn, newRow, current);
                    visited[newColumn][newRow] = newCell;
                    queue.offer(newCell);
                }
            }
        }

        System.out.println("No path found!");
    }

    /**
     * Checks if a move to the specified position is valid on the game map.
     *
     * @param map The GameMap object representing the game map.
     * @param column The column index of the position to check.
     * @param row The row index of the position to check.
     * @return True if the move is valid, false otherwise.
     */
    private static boolean isValidMove(GameMap map, int column, int row) {
        return column >= 0 && column < map.height && row >= 0 && row < map.width && map.grid[column][row] != '0';
    }

    /**
     * Prints the path from the start to the finish position on the game map.
     *
     * @param cell The Node object representing the finish position.
     */
    private static void printPath(Node cell) {
        List<String> path = new ArrayList<>();// Create a list to store the path steps
        int step = 1;

        // Traverse the path from finish to start
        while (cell != null) {
            if (cell.parent == null) {
                path.add(String.format("Start at (%d,%d)", cell.row + 1, cell.column + 1));
            } else {
                // Determine the direction of movement from the parent to the current cell
                String direction = "";
                if (cell.parent.column < cell.column) direction = "down";
                else if (cell.parent.column > cell.column) direction = "up";
                else if (cell.parent.row < cell.row) direction = "right";
                else if (cell.parent.row > cell.row) direction = "left";
                path.add(String.format("Move %s to (%d,%d)", direction, cell.row + 1, cell.column + 1));// Add the movement step to the path
            }
            cell = cell.parent;// Move to the parent cell
        }

        Collections.reverse(path);
        for (String s : path) {
            System.out.println(step + ". " + s);
            step++;
        }
        System.out.println("Done!");
        System.out.println();
    }
}
