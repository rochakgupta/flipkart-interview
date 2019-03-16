package com.flipkart.interview;

public class TestUtil {
    public static boolean areMatricesEqual(int[][] firstMatrix, int[][] secondMatrix) {
        int firstMatrixM = firstMatrix.length;
        int firstMatrixN = firstMatrix[0].length;
        int secondMatrixM = secondMatrix.length;
        int secondMatrixN = secondMatrix[0].length;
        if (firstMatrixM != secondMatrixM || firstMatrixN != secondMatrixN) {
            return false;
        }
        for (int i = 0; i < firstMatrixM; i++) {
            for (int j = 0; j < firstMatrixN; j++) {
                if (firstMatrix[i][j] != secondMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
