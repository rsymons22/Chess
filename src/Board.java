import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Board {

    private boolean isWhiteTurn;
    private boolean checkMate = false;
    private int pieceState;
    private ArrayList<RedDot> possibleMoves;
    private Piece pieceClickedOn;
    private final static Piece[] pieceArray = new Piece[32];
    private Piece[][] boardArray;

    private Pane pane;
    private Scene scene;
    private Stage primaryStage;

    private TextField statusMessage;
    
    public Board(BorderPane pane, Scene scene, Stage primaryStage, TextField textField) {
        this.pane = pane;
        this.scene = scene;
        this.primaryStage = primaryStage;
        statusMessage = textField;
        isWhiteTurn = true;
        checkMate = false;
        pieceState = Constants.CLICK_ON_PIECE;
        possibleMoves = new ArrayList<RedDot>();

        // TextArea textArea = new TextArea("Test");
        // textArea.setLayoutX(0);
        // textArea.setLayoutY(480);

        //pane.getChildren().addAll(initImage("board"));
        pane.setCenter(initImage("board"));
        initPieces();
        initBoardArray();

        pane.setOnMouseClicked(e -> {
            if(e.getY() <= 480) {
                if(!checkMate) {
                    if(isWhiteTurn) {
                        //System.out.println("white turn, piece state: " + pieceState);
                        turn(convertToRC((int)(e.getY())), convertToRC((int)(e.getX())), Constants.WHITE);
                    } else {
                        //System.out.println("black turn, piece state: " + pieceState);
                        turn(convertToRC((int)(e.getY())), convertToRC((int)(e.getX())), Constants.BLACK);
                    }
                }
            }
        });

    }

    private int convertToRC(int num) {
        int pixels = Constants.PIECE_LENGTH;
        for(int rc = 0; rc <= 7; rc++) {
            if(num <= pixels) {
                return rc;
            }
            pixels += 60;
        }

        System.out.println("Unexpected mouse click location");
        return -1;
    }

    public void printBoardArray() {
        for(int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(boardArray[i]));
        }
    }

    public Piece[][] getBoardArray() {
        return boardArray;
    }

    private void turn(int row, int column, int teamColor) {
        //System.out.println("turn method called on: " + row + "," + column);
        int oppositeTeamColor;

        if(teamColor == Constants.WHITE) {
            oppositeTeamColor = Constants.BLACK;
        } else {
            oppositeTeamColor = Constants.WHITE;
        }

        switch(pieceState) {
            case Constants.CLICK_ON_PIECE:
            //System.out.println("Click on piece state");
                pieceClickedOn = boardArray[row][column];
                //System.out.println("Piece clicked on: " + row + ", " + column + ": " + pieceClickedOn);
                if(pieceClickedOn != null) { // If they click on a piece
                    //System.out.println("Clicked on piece");
                    if(pieceClickedOn.getTeamColor() == teamColor) { // If the piece is on the team of whos turn it is
                        //System.out.println("Clicked on piece of: " + teamColor);
                        possibleMoves = pieceClickedOn.findMoves(); 
                        setDotsVisible();
                        if(possibleMoves.isEmpty()) {
                            //System.out.println("No possible moves, clearing");
                            removeRedDots();
                            possibleMoves.clear();
                        } else {
                            //System.out.println("Setting piece state to red dots placed");
                            pieceState = Constants.RED_DOTS_PLACED;
                        }
                    }
                }
                break;

            case Constants.RED_DOTS_PLACED:

                Piece king;
                Piece opposingKing;

                if(teamColor == Constants.WHITE) {
                    king = pieceArray[15];
                    opposingKing = pieceArray[31];
                } else {
                    king = pieceArray[31];
                    opposingKing = pieceArray[15];
                }
                
                Piece spotClickedOn = boardArray[row][column];

                if(spotClickedOn == null) {// If they click on an empty space

                    for (int i = 0; i < possibleMoves.size(); i++) {
                        if(row == possibleMoves.get(i).getRow() && 
                           column == possibleMoves.get(i).getColumn() && 
                           !checkMate) 
                        {
                            pieceClickedOn.phasePiece(row, column);
                            //printBoardArray();
                            //System.out.println("Calling is incheck first time case: RED DOTS PLACED and spot clicked on is null");
                            if(isInCheck(king, true)) {
                                pieceClickedOn.unPhasePiece();
                                removeRedDots();
                                possibleMoves.clear();
                                pieceState = Constants.CLICK_ON_PIECE;
                            } else {
                                pieceClickedOn.unPhasePiece();
                                pieceClickedOn.move(row, column);
                                removeRedDots();
                                possibleMoves.clear();
                                repaint();
                                pieceState = Constants.CLICK_ON_PIECE;
                                //update message other teams turn

                                if(teamColor == Constants.WHITE) {
                                    isWhiteTurn = false;
                                    statusMessage.setText("Black Turn");
                                } else {
                                    isWhiteTurn = true;
                                    statusMessage.setText("White Turn");
                                }
                                //System.out.println("Calling isincheck in else statement case: RED DOTS PLACED and spot clicked on is null");
                                if(isInCheck(opposingKing, true)) {
                                    isCheckMate(opposingKing);
                                };
                            }
                        }
                    }
                } else if(spotClickedOn == pieceClickedOn) {

                    //System.out.println("Clicked on same piece");
                    removeRedDots();
                    possibleMoves.clear();
                    pieceState = Constants.CLICK_ON_PIECE;
                } else {

                    if(spotClickedOn.getTeamColor() != teamColor) {

                        for (int i = 0; i < possibleMoves.size(); i++) {

                            if(row == possibleMoves.get(i).getRow() && 
                               column == possibleMoves.get(i).getColumn() &&
                               !checkMate) {

                                
                                ///spotClickedOn.phasePiece(-1, -1);
                                //System.out.println("Phasing to-be captured piece: " + spotClickedOn + "to: " + spotClickedOn.getRow() + ", " + spotClickedOn.getColumn());
                                pieceClickedOn.phasePiece(row, column);
                                

                                if(isInCheck(king, true)) {
                                    pieceClickedOn.unPhasePiece();
                                    //spotClickedOn.unPhasePiece();
                                    removeRedDots();
                                    possibleMoves.clear();
                                    pieceState = Constants.CLICK_ON_PIECE;
                                } else {
                                    pieceClickedOn.unPhasePiece();
                                    //spotClickedOn.unPhasePiece();
                                    spotClickedOn.removePiece();
                                    pieceClickedOn.move(row, column);
                                    removeRedDots();
                                    possibleMoves.clear();
                                    repaint();
                                    pieceState = Constants.CLICK_ON_PIECE;
                                    // update message other teams turn
                                    if(teamColor == Constants.WHITE) {
                                        isWhiteTurn = false;
                                        statusMessage.setText("Black Turn");
                                    } else {
                                        isWhiteTurn = true;
                                        statusMessage.setText("White Turn");
                                    }

                                    if(isInCheck(opposingKing, true)) {
                                        isCheckMate(opposingKing);
                                    };
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
        }
        
    }



    // Give the player an option of piece change instead of just queen
    public void changePawn(Piece piece, int teamColor) {

    }

    private boolean isInCheck(Piece piece, boolean updateMessage) {

        //System.out.println("Calling is In Check (0 = white): " + kingColor);

        for(int i = 0; i < 32; i++) { // loop through pieceArray

            if(!pieceArray[i].isCaptured() && (!pieceArray[i].isPhased())) { // check if the piece is captured or is phased
                ArrayList<RedDot> moves = pieceArray[i].findMoves(); // set the array to the possible moves of the piece

                for(int j = 0; j < moves.size(); j++) { // loop through the array of the possible moves

                    if(moves.get(j).getColumn() == piece.getCurrentColumn() && // if an imaginary red dot is on the opposite king
                      (moves.get(j).getRow() == piece.getCurrentRow()) &&
                       pieceArray[i].getTeamColor() != (piece.getTeamColor()))
                    {

                            System.out.println("Check by: " + pieceArray[i] + " at " + pieceArray[i].getCurrentRow() + ", " + pieceArray[i].getCurrentColumn() + " Has a move at " + moves.get(j).getRow() + ", " + moves.get(j).getColumn());
                            // System.out.println("Piece " + pieceArray[i] + " has moves at: ");
                            // for(RedDot move: moves) {
                            //     System.out.print("(" + move.getRow() + ", " + move.getColumn() + ")");
                            // }
                        
                        if(updateMessage) {
                            if(piece.getTeamColor() == Constants.WHITE) {
                                statusMessage.setText("White Turn - Check");
                            } else {
                                statusMessage.setText("Black Turn - Check");
                            }
                        }

                        // if(updateMessage) {
                        //     if(kingColor.equals("white"))
                        //     {
                        //         updateMessage("WTC");
                        //     }
                        //     else
                        //     {
                        //         updateMessage("BTC");
                        //     }
                        // }
                        return true;
                    }
                }
                removeRedDots();
            }
        }

        return false;
    }

    private boolean isCheckMate(Piece king) {
        //System.out.println("entering isCheckMate because of: " + checkingPiece);

        int startingPoint = 0;

        if(king.getTeamColor() == Constants.BLACK) {
            startingPoint = 16;
        }

        for(int i = startingPoint; i < (startingPoint + 15); i++) // Loops through all pieces on the team whose king is in check
        {
            if(!pieceArray[i].isCaptured()) {
            Piece piece = pieceArray[i];
            ArrayList<RedDot> moves = piece.findMoves();
            //System.out.println(kingColor + " " + pieceArray[i] + "piece being checked at i: " + i);
            for(int k = 0; k < moves.size(); k++) // Loop through their possible moves to see if one of the moves makes the check go away
            {
                piece.phasePiece(moves.get(k).getRow(), moves.get(k).getColumn());
                //System.out.println("going into isInCheck");
                if(!isInCheck(king, false))
                {
                    piece.unPhasePiece();
                    return false;
                }
                piece.unPhasePiece();
            }
            }
        }

        checkMate = true;

        if(king.getTeamColor() == Constants.WHITE) {
            statusMessage.setText("White king checkmated, Black wins!");
        } else {
            statusMessage.setText("Black king checkmated, White wins!");
        }

        //System.out.println("Checkmate");
        return true;
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

    private void initPieces() {
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

    public void setDotsVisible()
    {
        //System.out.println("Setting dots visible");
        for(int i = 0; i < possibleMoves.size(); i++)
        {
            possibleMoves.get(i).setLocation();
        }
    }

    public void removeRedDots()
    {
        for(int i = 0; i < possibleMoves.size(); i++)
        {
            possibleMoves.get(i).remove(pane);
        }
        repaint();
        
    }

    public void initBoardArray() {
    
        Piece[][] boardArray = {
            {pieceArray[25], pieceArray[27], pieceArray[29], pieceArray[30], pieceArray[31], pieceArray[28], pieceArray[26], pieceArray[24]},
            {pieceArray[16], pieceArray[17], pieceArray[18], pieceArray[19], pieceArray[20], pieceArray[21], pieceArray[22], pieceArray[23]},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {pieceArray[0], pieceArray[1], pieceArray[2], pieceArray[3], pieceArray[4], pieceArray[5], pieceArray[6], pieceArray[7]},
            {pieceArray[9], pieceArray[11], pieceArray[13], pieceArray[14], pieceArray[15], pieceArray[12], pieceArray[10], pieceArray[8]}
        };

        this.boardArray = boardArray;
    }

    public void repaint() {
        scene.getWindow().setOpacity(0.999); // This must be done to reset or "repaint" the javafx scene so the red dot's removal gets updated.
        scene.getWindow().setOpacity(1); // This simply updates the frame so it checks everything again, this is due to a bug in JavaFX. 
    }

}
