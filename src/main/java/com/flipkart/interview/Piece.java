package com.flipkart.interview;

public class Piece {
    protected int id;

    protected int m;

    protected int n;

    protected int[][] grid;

    public Piece(int _id, int[][] _grid) {
        id = _id;
        m = _grid.length;
        n = _grid[0].length;
        grid = Util.getMatrixCopy(_grid);
    }

    public Integer getId() {
        return id;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int[][] getGrid() {
        return grid;
    }
}
