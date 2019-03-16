package com.flipkart.interview;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Board {
    private int m;

    private int n;

    private Stack<State> states;

    public Board(int _m, int _n) {
        m = _m;
        n = _n;
        states = new Stack<State>();
        states.push(new State(new int[_m][_n]));
    }

    public State dropPiece(int y, Piece piece) throws Exception {
        int x = 0;
        State state = states.peek();
        if (canPieceBePlaced(x, y, piece, state)) {
            State intermediateState = getStateAfterDroppingPiece(x, y, piece, state);
            State nextState = getStateAfterClearingFilledRows(intermediateState);
            states.push(nextState);
            return nextState;
        } else {
            throw new Exception("Drop is invalid");
        }
    }

    public State placePiece(int x, int y, Piece piece) throws Exception {
        State state = states.peek();
        if (canPieceBePlaced(x, y, piece, state)) {
            return getStateAfterPlacingPiece(x, y, piece, state);
        } else {
            throw new Exception("Piece does not fit");
        }
    }

    private State getStateAfterDroppingPiece(int x, int y, Piece piece, State state) {
        int i = x;
        while (canPieceBePlaced(i, y, piece, state)) {
            ++i;
        }
        return getStateAfterPlacingPiece(--i, y, piece, state);
    }

    private State getStateAfterClearingFilledRows(State state) {
        int[][] grid = state.getGrid();
        Set<Integer> rowsToClear = new HashSet<Integer>();
        for (int i = m - 1; i >= 0; i--) {
            boolean isFilled = true;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    isFilled = false;
                }
            }
            if (isFilled) {
                rowsToClear.add(i);
            }
        }
        int[][] nextGrid = new int[m][n];
        int i = m - 1;
        int actualI = m - 1;
        while (i >= 0) {
            if (!rowsToClear.contains(i)) {
                for (int j = 0; j < n; j++) {
                    nextGrid[actualI][j] = grid[i][j];
                }
                actualI--;
            }
            i--;
        }
        return new State(nextGrid);
    }

    private boolean canPieceBePlaced(int x, int y, Piece piece, State state) {
        int[][] stateGrid = state.getGrid();
        int pieceM = piece.getM();
        int pieceN = piece.getN();
        int[][] pieceGrid = piece.getGrid();
        for (int pieceI = 0; pieceI < pieceM; pieceI++) {
            int stateI = x + pieceI;
            for (int pieceJ = 0; pieceJ < pieceN; pieceJ++) {
                int stateJ = y + pieceJ;
                if (!isBlockInBounds(stateI, stateJ)) {
                    return false;
                }
                int pieceBlock = pieceGrid[pieceI][pieceJ];
                int stateBlock = stateGrid[stateI][stateJ];
                if (pieceBlock == 1 && stateBlock == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBlockInBounds(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    private State getStateAfterPlacingPiece(int x, int y, Piece piece, State state) {
        int[][] stateGridAfterPlacingPiece = Util.getMatrixCopy(state.getGrid());
        int pieceM = piece.getM();
        int pieceN = piece.getN();
        int[][] pieceGrid = piece.getGrid();
        for (int pieceI = 0; pieceI < pieceM; pieceI++) {
            int stateI = x + pieceI;
            for (int pieceJ = 0; pieceJ < pieceN; pieceJ++) {
                int stateJ = y + pieceJ;
                int pieceBlock = pieceGrid[pieceI][pieceJ];
                if (pieceBlock == 1) {
                    stateGridAfterPlacingPiece[stateI][stateJ] = 1;
                }
            }
        }
        return new State(stateGridAfterPlacingPiece);
    }
}
