import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Board {

    private boolean isWhiteTurn;
    private boolean checkMate = false;
    private String pieceState;
    private ArrayList<RedDot> possibleMoves;
    private Piece pieceClickedOn;
    private final static Piece[] pieceArray = new Piece[32];
    private Piece[][] boardArray = {
        {pieceArray[16], pieceArray[17], pieceArray[18], pieceArray[19], pieceArray[20], pieceArray[21], pieceArray[22], pieceArray[23],},
        {pieceArray[24], pieceArray[26], pieceArray[28], pieceArray[30], pieceArray[31], pieceArray[29], pieceArray[27], pieceArray[25]},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {pieceArray[0], pieceArray[1], pieceArray[2], pieceArray[3], pieceArray[4], pieceArray[5], pieceArray[6], pieceArray[7],},
        {pieceArray[8], pieceArray[10], pieceArray[12], pieceArray[14], pieceArray[15], pieceArray[13], pieceArray[11], pieceArray[9]}
    };

    private Pane pane;
    private Scene scene;
    private Stage primaryStage;
    
    public Board(Pane pane, Scene scene, Stage primaryStage) {
        this.pane = pane;
        this.scene = scene;
        this.primaryStage = primaryStage;
        isWhiteTurn = true;
        checkMate = false;
        pieceState = "CLICK_ON_PIECE";
        possibleMoves = new ArrayList<RedDot>();

        pane.getChildren().add(initImage("board"));
        initPieces(this);
    }

    public void updateBoard() {
        for(int i = 0; i < 32; i++) {

        }
    }

    public Piece[][] getBoardArray() {
        return boardArray;
    }

    public void whiteTurn(int row, int column) {

    }

    public void blackturn(int row, int column) {

    }

    // Give the player an option of piece change instead of just queen
    public void changePawn(Piece piece, int teamColor) {

    }

    public boolean isInCheck(int kingColor, boolean updateMessage, boolean runCheckMate) {
        return false;
    }

    public boolean isCheckMate(int kingColor) {
        return false;
    }

    public Pane getPane() {
        return pane;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void initPieces(Board board) {
        for (int i = 0; i < 8; i++) {
            pieceArray[i] = new Pawn(Constants.WHITE, this, 6, i, initImage("whitePawn"));
        }

        pieceArray[8] = new Rook(Constants.WHITE, this, 7, 7, initImage("whiteRook"));
        pieceArray[9] = new Rook(Constants.WHITE, this, 7, 0, initImage("whiteRook"));
        pieceArray[10] = new Knight(Constants.WHITE, this, 7, 6, initImage("whiteKnight"));
        pieceArray[11] = new Knight(Constants.WHITE, this, 7, 1, initImage("whiteKnight"));
        pieceArray[12] = new Bishop(Constants.WHITE, this, 7, 5, initImage("whiteBishop"));
        pieceArray[13] = new Bishop(Constants.WHITE, this, 7, 2, initImage("whiteBishop"));
        pieceArray[14] = new Queen(Constants.WHITE, this, 7, 3, initImage("whiteQueen"));
        pieceArray[15] = new King(Constants.WHITE, this, 7, 4, initImage("whiteKing"));

        for (int i = 16; i < 24; i++) {
            pieceArray[i] = new Pawn(Constants.BLACK, this, 1, (i - 16), initImage("blackPawn"));
        }

        pieceArray[24] = new Rook(Constants.BLACK, this, 0, 7, initImage("blackRook"));
        pieceArray[25] = new Rook(Constants.BLACK, this, 0, 0, initImage("blackRook"));
        pieceArray[26] = new Knight(Constants.BLACK, this, 0, 6, initImage("blackKnight"));
        pieceArray[27] = new Knight(Constants.BLACK, this, 0, 1, initImage("blackKnight"));
        pieceArray[28] = new Bishop(Constants.BLACK, this, 0, 5, initImage("blackBishop"));
        pieceArray[29] = new Bishop(Constants.BLACK, this, 0, 2, initImage("blackBishop"));
        pieceArray[30] = new Queen(Constants.BLACK, this, 0, 3, initImage("blackQueen"));
        pieceArray[31] = new King(Constants.BLACK, this, 0, 4, initImage("blackKing"));
    }

    public static ImageView initImage(String imageName) {
        try {
            return new ImageView(new Image(new FileInputStream("images\\" + imageName + ".png")));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find image named: " + imageName + ".png");
        }
        return null;
    }
}
