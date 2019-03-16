package com.flipkart.interview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tetris {
    private Board board;

    private Map<Integer, Piece> pieceIdToPieceMap;

    public Tetris(int m, int n, List<Piece> pieces) {
        board = new Board(m, n);
        pieceIdToPieceMap = new HashMap<Integer, Piece>();
        for (Piece piece : pieces) {
            pieceIdToPieceMap.put(piece.getId(), piece);
        }
    }

    public State dropPiece(int y, int pieceId) throws Exception {
        Piece piece = getPiece(pieceId);
        return board.dropPiece(y, piece);
    }

    public State placePiece(int x, int y, int pieceId) throws Exception {
        Piece piece = getPiece(pieceId);
        return board.placePiece(x, y, piece);
    }

    private Piece getPiece(int pieceId) throws Exception {
        Piece piece = pieceIdToPieceMap.get(pieceId);
        if (piece == null) {
            throw new Exception("Piece is invalid");
        }
        return piece;
    }

}
