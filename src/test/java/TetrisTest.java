import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TetrisTest {
    private Tetris tetris;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initialize() {
        Piece linePiece = new LinePiece(1, new int[][] { { 1, 1, 1 } });
        Piece boxPiece = new BoxPiece(2, new int[][] { { 1, 1 }, { 1, 1 } });
        tetris = new Tetris(4, 4, Arrays.asList(linePiece, boxPiece));
    }

    @Test
    public void testDropPiece() throws Exception {
        tetris.dropPiece(0, 1);
        tetris.dropPiece(2, 2);
        State obtainedState = tetris.dropPiece(0, 2);
        int[][] expectedGrid = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 } };
        assertTrue(TestUtil.areMatricesEqual(expectedGrid, obtainedState.getGrid()));
    }

    @Test
    public void testDropPieceWhenPieceIsInvalid() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Piece is invalid");
        tetris.dropPiece(0, 3);
    }

    @Test
    public void testDropPieceWhenDropIsInvalid() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Drop is invalid");
        tetris.dropPiece(2, 1);
    }

    @Test
    public void testPlacePiece() throws Exception {
        tetris.dropPiece(0, 1);
        tetris.dropPiece(2, 2);
        State obtainedState = tetris.placePiece(1, 0, 2);
        int[][] expectedGrid = new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 } };
        assertTrue(TestUtil.areMatricesEqual(expectedGrid, obtainedState.getGrid()));
    }

    @Test
    public void testPlacePieceWhenPieceIsInvalid() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Piece is invalid");
        tetris.placePiece(3, 1, 3);
    }

    @Test
    public void testPlacePieceWhenPieceDoesNotFit() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Piece does not fit");
        tetris.dropPiece(0, 1);
        tetris.dropPiece(2, 2);
        tetris.placePiece(2, 0, 2);
    }
}
