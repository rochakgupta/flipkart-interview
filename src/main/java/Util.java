public class Util {
    public static int[][] getMatrixCopy(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = matrix[i][j];
            }
        }
        return copy;
    }
}
