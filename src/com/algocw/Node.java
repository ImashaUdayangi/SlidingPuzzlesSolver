package com.algocw;

/**
 * Author: S.P.Imasha Udayangi
 * Student ID: 20221357
 * UOW ID: w1956115
 */

public class Node {
    public int column, row;// Column index and Row index of the node

    public int distance;// Distance from the start node to this node

    public Node parent;// Parent node in the path

    /**
     * Constructor for the Node class.
     *
     * @param column The column index of the node.
     * @param row    The row index of the node.
     * @param parent The parent node in the path.
     */
    public Node(int column, int row, Node parent) {
        this.column = column;
        this.row = row;
        this.parent = parent;

        // Calculate the distance from the start node to this node
        this.distance = parent != null ? parent.distance + Math.abs(parent.column - column) + Math.abs(parent.row - row) : 0;
    }
}
