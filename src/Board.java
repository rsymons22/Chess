import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Board {

    private boolean isWhiteTurn;
    private boolean checkMate = false;
    private String pieceState;
    private ArrayList<RedDot> possibleMoves;
    private Piece pieceClickedOn;
    private Piece[][] boardArray = {
        {Constants.pieceArray[16], Constants.pieceArray[17], Constants.pieceArray[18], Constants.pieceArray[19], Constants.pieceArray[20], Constants.pieceArray[21], Constants.pieceArray[22], Constants.pieceArray[23],},
        {Constants.pieceArray[24], Constants.pieceArray[26], Constants.pieceArray[28], Constants.pieceArray[30], Constants.pieceArray[31], Constants.pieceArray[29], Constants.pieceArray[27], Constants.pieceArray[25]},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {Constants.pieceArray[0], Constants.pieceArray[1], Constants.pieceArray[2], Constants.pieceArray[3], Constants.pieceArray[4], Constants.pieceArray[5], Constants.pieceArray[6], Constants.pieceArray[7],},
        {Constants.pieceArray[8], Constants.pieceArray[10], Constants.pieceArray[12], Constants.pieceArray[14], Constants.pieceArray[15], Constants.pieceArray[13], Constants.pieceArray[11], Constants.pieceArray[9]}
    };
    
    public Board(Pane pane, Scene scene, Stage primaryStage) {
        Constants constants = new Constants(this);
        isWhiteTurn = true;
        checkMate = false;
        pieceState = "CLICK_ON_PIECE";
        possibleMoves = new ArrayList<RedDot>();
        
        pane.getChildren().add(Constants.boardImage);
    }
}
