public class State {
    private int[][] grid;

    public State(int[][] _grid) {
        grid = Util.getMatrixCopy(_grid);
    }

    public void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getGrid() {
        return grid;
    }
}
