import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class Constants {

    public Constants(Board board) {
        initPieces(board);
    }

    public final static int WHITE = 0;
    public final static int BLACK = 1;

    public final static ImageView boardImage = initImage("board");
    public final static ImageView redDotImage = initImage("redDot");

    public final static ImageView whitePawnImage = initImage("whitePawn");
    public final static ImageView whiteRookImage = initImage("whiteRook");
    public final static ImageView whiteBishopImage = initImage("whiteBishop");
    public final static ImageView whiteKnightImage = initImage("whiteKnight");
    public final static ImageView whiteQueenImage = initImage("whiteQueen");
    public final static ImageView whiteKingImage = initImage("whiteKing");

    public final static ImageView blackPawnImage = initImage("blackPawn");
    public final static ImageView blackRookImage = initImage("blackRook");
    public final static ImageView blackBishopImage = initImage("blackBishop");
    public final static ImageView blackKnightImage = initImage("blackKnight");
    public final static ImageView blackQueenImage = initImage("blackQueen");
    public final static ImageView blackKingImage = initImage("blackKing");

    public final static Piece[] pieceArray = new Piece[32];

    public void initPieces(Board board) {
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(WHITE, board, 6, i);
            pieceArray[i] = pawn;
        }

        Rook whiteRook1 = new Rook(WHITE, board, 7, 7);
        pieceArray[8] = whiteRook1;
        Rook whiteRook2 = new Rook(WHITE, board, 7, 0);
        pieceArray[9] = whiteRook2;

        Knight whiteKnight1 = new Knight(WHITE, board, 7, 6);
        pieceArray[10] = whiteKnight1;
        Knight whiteKnight2 = new Knight(WHITE, board, 7, 1);
        pieceArray[11] = whiteKnight2;

        Bishop whiteBishop1 = new Bishop(WHITE, board, 7, 5);
        pieceArray[12] = whiteBishop1;
        Bishop whiteBishop2 = new Bishop(WHITE, board, 7, 2);
        pieceArray[13] = whiteBishop2;

        Queen whiteQueen = new Queen(WHITE, board, 7, 3);
        pieceArray[14] = whiteQueen;
        King whiteKing = new King(WHITE, board, 7, 4);
        pieceArray[15] = whiteKing;

        for (int i = 16; i < 24; i++) {
            Pawn pawn = new Pawn(WHITE, board, 1, (i - 16));
            pieceArray[i] = pawn;
        }

        Rook blackRook1 = new Rook(BLACK, board, 0, 7);
        pieceArray[24] = blackRook1;
        Rook blackRook2 = new Rook(BLACK, board, 0, 0);
        pieceArray[25] = blackRook2;

        Knight blackKnight1 = new Knight(BLACK, board, 0, 6);
        pieceArray[26] = blackKnight1;
        Knight blackKnight2 = new Knight(BLACK, board, 0, 1);
        pieceArray[27] = blackKnight2;

        Bishop blackBishop1 = new Bishop(BLACK, board, 0, 5);
        pieceArray[28] = blackBishop1;
        Bishop blackBishop2 = new Bishop(BLACK, board, 0, 2);
        pieceArray[29] = blackBishop2;

        Queen blackQueen = new Queen(BLACK, board, 0, 3);
        pieceArray[30] = blackQueen;
        King blackKing = new King(BLACK, board, 0, 4);
        pieceArray[31] = blackKing;
    }

    public static ImageView initImage(String imageName) {
        try {
            return new ImageView(new Image(new FileInputStream("images\\board.png")));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find image named: " + imageName + ".png");
        }
        return null;
    }
}
